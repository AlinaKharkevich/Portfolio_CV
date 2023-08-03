import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn import datasets
from plotka import plot_decision_regions
from sklearn.linear_model import LogisticRegression
from matplotlib.colors import ListedColormap
import matplotlib.pyplot as plt
import numpy as np

def plot_decision_regions(X, y, classifier, test_idx=None, resolution=0.02):

        markers = ('s', 'x', 'o', '^', 'v')
        colors = ('red', 'blue', 'lightgreen', 'gray', 'cyan')
        cmap = ListedColormap(colors[:len(np.unique(y))])

        x1_min, x1_max = X[:, 0].min() - 1, X[:, 0].max() + 1
        x2_min, x2_max = X[:, 1].min() - 1, X[:, 1].max() + 1
        xx1, xx2 = np.meshgrid(np.arange(x1_min, x1_max, resolution), np.arange(x2_min, x2_max, resolution))
        Z = classifier.predict(np.array([xx1.ravel(), xx2.ravel()]).T)
        Z = Z.reshape(xx1.shape)
        plt.contourf(xx1, xx2, Z, alpha=0.3, cmap=cmap)
        plt.xlim(xx1.min(), xx1.max())
        plt.ylim(xx2.min(), xx2.max())

        for idx, cl in enumerate(np.unique(y)):
            plt.scatter(x=X[y == cl, 0], y=X[y == cl, 1], alpha=0.8, c=cmap(idx), marker=markers[idx], label=cl, edgecolor='black')

class Perceptron(object):

    def __init__(self, eta=0.01, n_iter=10):
        self.eta = eta
        self.n_iter = n_iter

    def fit(self, X, y):
        self.w_ = np.zeros(1+ X.shape[1])
        self.errors_ = []

        for _ in range(self.n_iter):
            errors = 0
            for xi, target in zip(X,y):
                update = self.eta * (target - self.predict(xi))
                self.w_[1:] += update *xi
                self.w_[0] += update
                errors += int(update != 0.0)
            self.errors_.append(errors)
        return self

    def net_input(self, X):
        return np.dot(X, self.w_[1:]) + self.w_[0]

    def predict(self, X):
        return np.where(self.net_input(X) >= 0.0, 1, -1)
    
    
class LogisticRegressionGD(object):
    def __init__(self, eta=0.05, n_iter=100, random_state=1):
        self.eta = eta
        self.n_iter = n_iter
        self.random_state = random_state

    def fit(self, X, y):
        rgen = np.random.RandomState(self.random_state)
        self.w_ = rgen.normal(loc=0.0, scale=0.01, size=1 + X.shape[1])
        self.cost_ = []

        for i in range(self.n_iter):
            net_input = self.net_input(X)
            output = self.activation(net_input)
            errors = (y - output)
            self.w_[1:] += self.eta * X.T.dot(errors)
            self.w_[0] += self.eta * errors.sum()
            cost = (-y.dot(np.log(output)) - ((1 - y).dot(np.log(1 - output))))
            self.cost_.append(cost)
    
        return self

    def net_input(self, X):
        return np.dot(X, self.w_[1:]) + self.w_[0]

    def activation(self, z):
        return 1. / (1. + np.exp(-np.clip(z, -250, 250)))

    def predict(self, X): 
        return np.where(self.net_input(X) >= 0.0, 1, -1)
    
    def predict_proba(self, X):# Probabilith Method, estimates of  input data X by applying sigmoid function to net_input method output
        return self.activation(self.net_input(X))


class Multiclassifier(object):
    def __init__(self,eta=0.01, n_iter=10,num=2): # eta - learning rate, n_iter - amount to study
        self.eta = eta
        self.n_iter = n_iter
        self.ppn = []
        self.num = num
        for i in range(num-1): # list of perceptrons for each class
            self.ppn.append(Perceptron(eta=eta, n_iter=n_iter))
    
    def multiFit(self, X, Y, i): # x - training, y - characters
        self.ppn[i].fit(X, Y)

    def predict(self, X): # predicts the class for each perceptron for each perseptron in ppn list
        res = []
        for p in self.ppn:
            res.append(p.predict(X))
        res1 = res[0].copy()
        res1[(res1 == 1)] = 0

        for j in range(len(res1)):
            if res1[j] == -1:
                for l in range(len(res) - 1):
                    if not res[l+1][j] == -1:
                        res1[j] = res[l+1][j]
                        break
                if res1[j] == -1:
                    res1[j] = self.num-1
        return res1

class MulticlassifierLogReg(object):
    def __init__(self,eta=0.01, n_iter=10,num=2):
        self.eta = eta
        self.n_iter = n_iter
        self.ppn = []
        self.num = num

        for i in range(num-1): # results of LRGD to pnn
            self.ppn.append(LogisticRegressionGD(eta=eta, n_iter=n_iter))

    def multiFit(self, X, Y, i): # train
        self.ppn[i].fit(X, Y)

    def predict(self, X): #classification result
        res = []
        for p in self.ppn:
            res.append(p.predict(X))
        res1 = res[0].copy()
        res1[(res1 == 1)] = 0

        for j in range(len(res1)): # match the results
            if res1[j] == -1:
                for l in range(len(res) - 1):
                    if not res[l+1][j] == -1:
                        res1[j] = res[l+1][j]
                        break
                if res1[j] == -1:
                    res1[j] = self.num-1
        return res1

def main():
    iris = datasets.load_iris()
    X = iris.data[:, [2, 3]]
    y = iris.target
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=1, stratify=y) # Divide into test and train

    mpp = Multiclassifier(eta= 0.01, n_iter=500, num=3) #Task 1
    mppLinReg = MulticlassifierLogReg(eta=0.01, n_iter=2000, num=3) #Task 2

    for i in range (len(iris.target_names) - 1):
        X1 = X_train.copy()
        Y1 = y_train.copy()
        X1 = X1[(Y1 == i) | (Y1 == i+1)]
        Y1 = Y1[(Y1 == i) | (Y1 == i+1)]
        Y1[(Y1 != i)] = -1
        Y1[(Y1 == i)] = 1
        mpp.multiFit(X1, Y1, i) #Task 1

        Y1[(Y1 == -1)] = 0
        mppLinReg.multiFit(X1, Y1, i) #Task 2

    # Print task 1
    plot_decision_regions(X= X_test, y=y_test, classifier=mpp)
    plt.title('Task 1')
    plt.xlabel(r'$x_1$')
    plt.ylabel(r'$x_2$')
    plt.legend(loc='upper left')
    plt.show()
    
    # Print task 2
    plot_decision_regions(X=X_test, y=y_test, classifier=mppLinReg)
    plt.title('Task 2')
    plt.xlabel(r'$x_1$')
    plt.ylabel(r'$x_2$')
    plt.legend(loc='upper left')
    plt.show()

    iris_prob = datasets.load_iris()
    X = iris_prob.data[:, :2] # Take only first two characteristics
    y = (iris_prob.target != 0) * 1
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=0)

    lr = LogisticRegression()
    lr.fit(X_train, y_train) #match to trainung data

    proba = lr.predict_proba(X_test) #Calculate probability

    # Print task 3
    print(proba)
    plot_decision_regions(X=X_train, y=y_train, classifier=lr)
    plt.title('Task 3')
    plt.xlabel(r'$x_1$')
    plt.ylabel(r'$x_2$')
    plt.legend(loc='upper left')
    plt.show()

if __name__ == '__main__':
    main()