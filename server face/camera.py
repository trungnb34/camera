import cv2

video = cv2.VideoCapture(0)

check , frame = video.read()

gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

print(gray.shape)

video.release()

cv2.destroyAllWindows