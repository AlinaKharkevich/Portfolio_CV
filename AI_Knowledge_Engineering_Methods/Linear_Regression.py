import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split

# Load data
data = np.loadtxt('dane2.txt')
X = data[:, 0]
y = data[:, 1]

# Split data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# Define Model 1
def model1(x, w):
    return w[0] + w[1]*x

# Identify model parameters for Model 1 using least squares method
X_train_1 = np.vstack((np.ones(len(X_train)), X_train)).T
w_1 = np.linalg.lstsq(X_train_1, y_train, rcond=None)[0]

# Verify quality of Model 1
y_pred_train_1 = model1(X_train, w_1)
y_pred_test_1 = model1(X_test, w_1)

print("Model 1 parameters: ", w_1)
print("Model 1 training MSE: ", np.mean((y_pred_train_1 - y_train) ** 2))
print("Model 1 test MSE: ", np.mean((y_pred_test_1 - y_test) ** 2))

# Define Model 2
def model2(x, w):
    return w[0] + w[1]*x + w[2]*x**2

# Identify model parameters for Model 2 using least squares method
X_train_2 = np.vstack((np.ones(len(X_train)), X_train, X_train**2)).T
w_2 = np.linalg.lstsq(X_train_2, y_train, rcond=None)[0]

# Verify quality of Model 2
y_pred_train_2 = model2(X_train, w_2)
y_pred_test_2 = model2(X_test, w_2)

print("Model 2 parameters: ", w_2)
print("Model 2 training MSE: ", np.mean((y_pred_train_2 - y_train) ** 2))
print("Model 2 test MSE: ", np.mean((y_pred_test_2 - y_test) ** 2))

# Compare models using plots
x_plot = np.linspace(X.min(), X.max(), 1000)
y_plot_1 = model1(x_plot, w_1)
y_plot_2 = model2(x_plot, w_2)

plt.figure(figsize=(8, 6))
plt.scatter(X_train, y_train, label='Training data')
plt.scatter(X_test, y_test, label='Test data')
plt.plot(x_plot, y_plot_1, label='Model 1')
plt.plot(x_plot, y_plot_2, label='Model 2')
plt.legend()
plt.show()