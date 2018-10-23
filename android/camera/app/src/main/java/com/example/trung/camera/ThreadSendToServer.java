package com.example.trung.camera;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ThreadSendToServer extends Thread {

    int port = 4444;

    String ip = "192.168.2.243";
    DatagramPacket packet;

    @Override
    public void run() {
        try {
            DatagramSocket udpSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(ip);
            int a = 0;
//            while (true) {
//                byte[] bytes = new byte[(int) (MainActivity.mRgba.total() * MainActivity.mRgba.channels())];
//                packet = new DatagramPacket(bytes, bytes.length, serverAddress, port);
//                udpSocket.send(packet);
//                Thread.sleep(10);
//                a ++;
//            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

}
