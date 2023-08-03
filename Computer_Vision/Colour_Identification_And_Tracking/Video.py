import cv2
import sys
import opencv_jupyter_ui as jcv2
import numpy as np
import matplotlib.pyplot as plt

# Trace the red ball as above (for each frame of film) (3 points).
# Uses tasks one script
def paint(frame):
    img_hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    mask = create_mask(img_hsv, "RED")
    res = cv2.bitwise_and(frame, frame, mask=mask)
    return res

def create_mask(img_hsv, color = "RED"):
    lowerBoundary = np.array([0,50,50])
    upperBoundary = np.array([10,255,255])
    mask0 = cv2.inRange(img_hsv, lowerBoundary, upperBoundary)

    lowerBoundary = np.array([170,100,50])
    upperBoundary = np.array([180,255,255])
    mask1 = cv2.inRange(img_hsv, lowerBoundary, upperBoundary)

    mask = mask0+mask1
    knl = np.ones((7,7), np.uint8)
    mask = cv2.morphologyEx(mask, cv2.MORPH_CLOSE, knl)
    mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, knl)
    return mask

#Import the 'rgb_ball_720.mp4' video and set the condition for correct video import, e.g. using cap.read()
cap = cv2.VideoCapture('rgb_ball_720.mp4')

if (cap.isOpened()== False):
    print("Error opening video")
while cap.isOpened():
    cv2.startWindowThread()
    ret, frame = cap.read()
    width = int(cap.get(3))
    height = int(cap.get(4))
    if not ret:
        print("Exit")
        break
    det_frame = paint(frame)
    cv2.imshow('Click q to EXIT', det_frame)
    if cv2.waitKey(1) == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()