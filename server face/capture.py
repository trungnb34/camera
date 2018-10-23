import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# sock.sendto("connection".encode(), ('127.0.0.1', 4242))

count = 0

while True:

    MESSAGE = str(count).encode()
    count = count + 1
    sock.sendto(MESSAGE, ('192.168.1.3', 4444))
    