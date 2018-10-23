import cv2
import numpy as np
import os

from os.path import isfile, join

def convert_frames_to_video(pathIn,pathOut,fps):
    frame_array = []
    files = [f for f in os.listdir(pathIn) if isfile(join(pathIn, f))]


    #for sorting the file names properly
    # files.sort(key = lambda x: int(x[5:-4]))

    for i in range(1663):
        try:
            filename=pathIn + str(i) + ".jpg"
            #reading each files
            img = cv2.imread(filename)
            height, width, layers = img.shape
            size = (width,height)
            print(filename)
            #inserting the frames into an image array
            frame_array.append(img)
        except:
            pass

    out = cv2.VideoWriter(pathOut,cv2.VideoWriter_fourcc(*'DIVX'), fps, size)

    for i in range(len(frame_array)):
        out.write(frame_array[i])
    out.release()

def main():
    pathIn= './images/'
    pathOut = 'video.avi'
    fps = 8
    convert_frames_to_video(pathIn, pathOut, fps)

if __name__=="__main__":
    main()