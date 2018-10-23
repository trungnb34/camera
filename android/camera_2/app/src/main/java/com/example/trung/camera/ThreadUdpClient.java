package com.example.trung.camera;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThreadUdpClient extends Thread {

    public static int port = -1;
    public static String ip = "none";

    DatagramPacket packet;
    DatagramSocket udp_socket;
    InetAddress serverAddress;

    ThreadUdpClient() {
        try {
            udp_socket = new DatagramSocket();
            serverAddress = InetAddress.getByName(ip);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                byte[] bytes = (Integer.toString(MainActivity.i)).getBytes();
                packet = new DatagramPacket(bytes, bytes.length, serverAddress, port);
                udp_socket.send(packet);
                i++;
                Thread.sleep(10);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }
}
