import socket
import numpy as np
import cv2
import time
import matplotlib.pyplot as plt
import struct

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
UDP = ('0.0.0.0', 4445)

sock.bind(UDP)

print(UDP)

rev_data = []

index = 0

BUFF = 51840
COUT_PART = 10

count = 0
ok = 0

ids = 0

while True:
    data, addr = sock.recvfrom(BUFF)
    # print("connect...{}".format(ids))
    # ids += 1
    if data != b'd':
        for item in data:
            rev_data.append(item)
    else:
        # print(len(rev_data))
        # rev_data = []
        length_rev_data = len(rev_data)
        # count += 1
        if length_rev_data == BUFF * COUT_PART:
            # ok += 1
            image = np.reshape(rev_data, (540, 960))
            cv2.imwrite("images/{}.jpg".format(index), image)
            index += 1
            print("success")
        else:
            print("fail...{}".format(length_rev_data))

        # if length_rev_data < BUFF * COUT_PART:
        #     array = np.zeros(BUFF * COUT_PART - length_rev_data)
        #     rev_data = np.append(rev_data, array)
        # image = np.reshape(rev_data, (540, 960))
        # cv2.imwrite("images/{}.jpg".format(index), image)
        rev_data = []
        # index += 1
    
    # if count % 20 == 0 and count > 0:
    #     print("=====================")
    #     print(count)
    #     print(ok)
    #     count = 0
    #     ok = 0
    #     print("=====================")