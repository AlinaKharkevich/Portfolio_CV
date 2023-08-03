import numpy as np
import cv2 as cv

img = cv.imread('tray8.jpg', cv.IMREAD_COLOR)
img = cv.medianBlur(img,5) 

imgGray = cv.cvtColor(img,cv.COLOR_BGR2GRAY)
ret, thresh = cv.threshold(imgGray, 127, 255, 0)
contours, hierarchy = cv.findContours(thresh, cv.RETR_TREE, cv.CHAIN_APPROX_SIMPLE)

#finding tray countour by looking for the countour with the biggest area
imax=0
areamax=0
for i in range(len(contours)):
    temp = contours[i]
    area = cv.contourArea(temp)
    if area > areamax:
        imax=i
        areamax=area
tray = contours[imax]
area = cv.contourArea(tray)
cv.drawContours(img, [tray], 0, (0,255,0), 3)

imgOrignal = cv.putText(img,"Area = "+str(areamax), (10,50),fontFace=cv.FONT_HERSHEY_DUPLEX, fontScale=1.0, color=(255,0,0), thickness=1)
#cv.imshow('COINS',imgOrignal)
#_______________________________________________

#The function finds circles in a grayscale image using a modification of the Hough transform.
circles = cv.HoughCircles(imgGray,method = cv.HOUGH_GRADIENT,dp = 1,minDist = 20,param1=50,param2=50,minRadius=0,maxRadius=50)
circles = np.uint16(np.around(circles))

for i in circles[0,:]:
    # draw the outer circle
    cv.circle(img,(i[0],i[1]),i[2],(255,255,255),2)
    # draw the center of the circle
    cv.circle(img,(i[0],i[1]),2,(0,0,255),3)


BigCoinInTray=0
SmallCoinInTray=0
BigCoinOutTray=0
SmallCoinOutTray=0

for i in circles[0,:]:
    if cv.pointPolygonTest(tray,(i[0],i[1]),False)>-1:
        if i[2] > 35:
            BigCoinInTray=BigCoinInTray+1
        else:
            SmallCoinInTray=SmallCoinInTray+1
    else:
        if i[2] > 35:
            BigCoinOutTray=BigCoinOutTray+1
        else:
            SmallCoinOutTray=SmallCoinOutTray+1
print(BigCoinInTray)
print(SmallCoinInTray)
print(BigCoinOutTray)
print(SmallCoinOutTray)

#imgOrignal = cv.putText(img,"Area = "+str(areamax), (50,50),fontFace=cv.FONT_HERSHEY_DUPLEX, fontScale=1.0, color=(255,0,0), thickness=1)

imgOrignal = cv.putText(img,"BigCoinInTray = "+str(BigCoinInTray), (10,80),fontFace=cv.FONT_HERSHEY_DUPLEX, fontScale=1.0, color=(255,0,0), thickness=1)
imgOrignal = cv.putText(img,"BigCoinOutTray = "+str(BigCoinOutTray), (10,110),fontFace=cv.FONT_HERSHEY_DUPLEX, fontScale=1.0, color=(255,0,0), thickness=1)
imgOrignal = cv.putText(img,"SmallCoinInTray = "+str(SmallCoinInTray), (380,50),fontFace=cv.FONT_HERSHEY_DUPLEX, fontScale=1.0, color=(255,0,0), thickness=1)
imgOrignal = cv.putText(img,"SmallCoinOutTray = "+str(SmallCoinOutTray), (380,80),fontFace=cv.FONT_HERSHEY_DUPLEX, fontScale=1.0, color=(255,0,0), thickness=1)
cv.imshow('detected circles',img)
cv.waitKey(0)
cv.destroyAllWindows()
