from keras.datasets import cifar10
from matplotlib import pyplot as plt
from keras.utils import to_categorical
from keras.models import Sequential
from keras.layers import Conv2D, MaxPooling2D, Flatten, Dense

# Method, which selects output data between two classes (vehicle, animal)
def data_selection(y_data):
    iterations = len(y_data)

    for i in range(iterations):
        if y_data[i] in [0, 1, 8, 9]:  # Vehicle
            y_data[i] = 0
        elif y_data[i] in [2, 3, 4, 5, 6, 7]:  # Animal
            y_data[i] = 1

# Load and split data
(X_train, y_train), (X_test, y_test) = cifar10.load_data()

# Normalize X_train and X_test
X_train = X_train / 255.0
X_test = X_test / 255.0

classes = ['Vehicle', 'Animal'] #class labels

# Perform data selection for y_train and y_test
data_selection(y_train)
data_selection(y_test)

# Display images with suitable class name
plt.figure(figsize=(10, 10))

images_quantity = 10 # number of images to display
for i in range(images_quantity):
    plt.subplot(1, 10, i+1)
    plt.xticks([])
    plt.yticks([])
    plt.grid(False)
    plt.imshow(X_train[i], cmap=plt.cm.binary) # Display the image
    plt.xlabel(classes[y_train[i][0]]) ## Set the x-label as the corresponding class name

plt.show()

# Convert y_train and y_test labels to one-hot encoding
y_train = to_categorical(y_train)
y_test = to_categorical(y_test)

# Define the model
model = Sequential() # Create a Sequential model
model.add(Conv2D(32, (3, 3), activation='relu', input_shape=(32, 32, 3))) # Add a convolutional layer with 32 filters and ReLU activation
model.add(MaxPooling2D((2, 2)))
model.add(Flatten())
model.add(Dense(2, activation='softmax'))

model.compile(optimizer='rmsprop', loss='categorical_crossentropy', metrics=['accuracy']) ## Compile the model with RMSprop optimizer, categorical cross-entropy loss, and accuracy metric
history = model.fit(X_train, y_train, epochs=4, batch_size=64, validation_data=(X_test, y_test), verbose=0) ## Train the model on the training data for 4 epochs, using a batch size of 64, and validate on the test data. 


model.save('miw_6.h5') # Save the trained model 

from keras.models import load_model

model = load_model('miw_6.h5')
loss, acc = model.evaluate(X_test, y_test, verbose=0) ## Evaluate the loaded model on the test data and store the loss and accuracy in 'loss' and 'acc' variables, respectively

plt.plot(history.history['loss'])  # Plot the training loss from the training history
plt.plot(history.history['val_loss'])  # Plot the validation loss
plt.ylabel('loss')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show() # Show the plot of loss values

plt.plot(history.history['accuracy']) # Plot the training accuracy 
plt.plot(history.history['val_accuracy']) # Plot the validation accuracy
plt.ylabel('accuracy')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()# Show the plot of accuracy values