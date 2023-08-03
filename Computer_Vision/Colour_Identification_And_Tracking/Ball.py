import cv2
import sys
import numpy as np
import matplotlib.pyplot as plt
#from matplotlib import pyplot
# 1 - Import libraries: cv2 (OpenCV), numpy, sys (1 point).

def printImg(imLoad, title = ""):
    if imLoad is None:
        sys.exit("No image") 
    imLoad = cv2.cvtColor(imLoad, cv2.COLOR_BGR2RGB)#otherwise the ball is blue
    plt.imshow(imLoad)
    plt.show()
    #cv2.imwrite("Output", imLoad)
    # 3 - Set the condition for the correct loading of the image, e.g. using the 'sys.exit' command (1 point).

imLoad = cv2.imread("red_ball.jpg")
 # 2 - Import photo red_ball.jpg (1 point).
printImg(imLoad)

img_hsv = cv2.cvtColor(imLoad, cv2.COLOR_BGR2HSV)
h,s,v = cv2.split(img_hsv)
hsv = cv2.merge((h, s, v))
printImg(hsv, "hsv")
#Change the image format to HSV (1 point).

lowerBoundary = np.array([0,50,50])
upperBoundary = np.array([10,255,255])
mask0 = cv2.inRange(img_hsv, lowerBoundary, upperBoundary)

lowerBoundary = np.array([170,100,50])
upperBoundary = np.array([180,255,255])
mask1 = cv2.inRange(img_hsv, lowerBoundary, upperBoundary)

mask = mask0+mask1
printImg(mask, "red")
#Find the RED color using a binary operation (1 point).

knl = np.ones((5,5), np.uint8) #8 bit integer matrix.
mask = cv2.morphologyEx(mask, cv2.MORPH_CLOSE, knl) #black noise
mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, knl) #wite noise
printImg(mask, "Noise")
#Improve image quality (remove noise) through morphological operations (1 point).

ball = cv2.bitwise_and(imLoad, imLoad, mask=mask)
gray = cv2.cvtColor(ball, cv2.COLOR_BGR2GRAY)
ret, thresh = cv2.threshold(gray, 0, 255, 0)#get binary image from greyscale
momentsNum = cv2.moments(thresh)
print(momentsNum)
#{'m00': 9765735.0, 'm10': 2802087900.0, 'm01': 2339150190.0, 
# 'm20': 833302962270.0, 'm11': 670806016395.0, 'm02': 590579522640.0, 
# 'm30': 255926400817410.0, 'm21': 199344909812055.0, 
# 'm12': 169266773194395.0, 'm03': 156017377949010.0, 'mu20': 29298286807.639977, 
# 'mu11': -367678537.5065027, 'mu02': 30291575918.42333, 
# 'mu30': 13159518136.662428, 'mu21': -42053622787.34966,
#  'mu12': -12407915432.941559, 'mu03': 46803924277.17812,
#  'nu20': 0.0003072078830579116, 'nu11': -3.85530204871048e-06,
#  'nu02': 0.0003176230396502301, 'nu30': 4.415477223805014e-08,
#  'nu21': -1.4110456907894396e-07, 'nu12': -4.163288307374715e-08, 'nu03': 1.5704348706721638e-07}
coordX = int(momentsNum["m10"] / momentsNum["m00"])
coordY = int(momentsNum["m01"] / momentsNum["m00"])
cv2.circle(imLoad, (coordX, coordY), 5, (0, 0, 255), -1)
#Add the calculated center of gravity of the ball to the image (1 point).

cv2.putText(imLoad, "red ball", (coordX - 25, coordY - 25), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 2)
#Add the word "red ball" near the center of gravity (1 point).
printImg(imLoad)