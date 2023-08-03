from sklearn.datasets import make_moons
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier, VotingClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score
from matplotlib.colors import ListedColormap
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import style
style.use('ggplot') # set style to ggplot
from plotka import plot_decision_regions

# create dataset
X, y = make_moons(n_samples=10000, noise=0.4, random_state=42)

# split into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# create decision tree classifiers with entropy and gini
tree_entropy = DecisionTreeClassifier(criterion='entropy', max_depth=5, random_state=42)
tree_entropy.fit(X_train, y_train)

tree_gini = DecisionTreeClassifier(criterion='gini', max_depth=5, random_state=42)
tree_gini.fit(X_train, y_train)

# evaluate decision tree classifiers
y_pred_entropy = tree_entropy.predict(X_test)
accuracy_entropy = accuracy_score(y_test, y_pred_entropy)
print("Decision Tree with Entropy Accuracy:", accuracy_entropy)

y_pred_gini = tree_gini.predict(X_test)
accuracy_gini = accuracy_score(y_test, y_pred_gini)
print("Decision Tree with Gini Accuracy:", accuracy_gini)

# create random forest classifiers with 10 and 100 trees
rfc_10 = RandomForestClassifier(n_estimators=10, random_state=42)
rfc_10.fit(X_train, y_train)

rfc_100 = RandomForestClassifier(n_estimators=100, random_state=42)
rfc_100.fit(X_train, y_train)

# evaluate random forest classifiers
y_pred_rfc_10 = rfc_10.predict(X_test)
accuracy_rfc_10 = accuracy_score(y_test, y_pred_rfc_10)
print("Random Forest with 10 Trees Accuracy:", accuracy_rfc_10)

y_pred_rfc_100 = rfc_100.predict(X_test)
accuracy_rfc_100 = accuracy_score(y_test, y_pred_rfc_100)
print("Random Forest with 100 Trees Accuracy:", accuracy_rfc_100)

# create logistic regression and SVM classifiers
lr = LogisticRegression(random_state=42)
lr.fit(X_train, y_train)

svm = SVC(random_state=42)
svm.fit(X_train, y_train)

# create voting classifier
voting_clf = VotingClassifier(estimators=[('lr', lr), ('rfc', rfc_100), ('svm', svm)], voting='hard')
voting_clf.fit(X_train, y_train)

# evaluate voting classifier
y_pred_voting = voting_clf.predict(X_test)
accuracy_voting = accuracy_score(y_test, y_pred_voting)
print("Voting Classifier Accuracy:", accuracy_voting)

# plot decision regions for the voting classifier
plt.figure(figsize=(8, 6))
plot_decision_regions(X_test, y_test, classifier=voting_clf)
plt.title('Voting Classifier Decision Boundary')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend(loc='lower right')
plt.show()
 #ctrl k-c u
# # tree entropy
# plt.figure(figsize=(8, 6))
# plot_decision_regions(X_test, y_test, classifier=tree_entropy)
# plt.title('Decision Tree with Entropy Decision Boundary')
# plt.xlabel('Feature 1')
# plt.ylabel('Feature 2')
# plt.legend(loc='lower right')
# plt.show()

# # tree gini
# plt.figure(figsize=(8, 6))
# plot_decision_regions(X_test, y_test, classifier=tree_gini)
# plt.title('Decision Tree with Gini Decision Boundary')
# plt.xlabel('Feature 1')
# plt.ylabel('Feature 2')
# plt.legend(loc='lower right')
# plt.show()

# # rfc 10
# plt.figure(figsize=(8, 6))
# plot_decision_regions(X_test, y_test, classifier=rfc_10)
# plt.title('Random Forest with 10 Trees Decision Boundary')
# plt.xlabel('Feature 1')
# plt.ylabel('Feature 2')
# plt.legend(loc='lower right')
# plt.show()

# # rfc 100
# plt.figure(figsize=(8, 6))
# plot_decision_regions(X_test, y_test, classifier=rfc_100)
# plt.title('Random Forest with 100 Trees Decision Boundary')
# plt.xlabel('Feature 1')
# plt.ylabel('Feature 2')
# plt.legend(loc='lower right')
# plt.show()