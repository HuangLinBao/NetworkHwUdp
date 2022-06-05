package com.network;
import java.io.*;
import java.net.*;
public class Main {

    public static void main(String[] args)throws  Exception {
        System.out.println("Enter Course Name");//give an interactive window
        System.out.println("Enter Q to exit");
        while (true) {
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));//BufferedReader to read input from cli

            DatagramSocket clientSocket = new DatagramSocket();//UDP socket

            InetAddress IPAddress = InetAddress.getByName("192.168.122.251");//VM server IP

            byte[] sendData = new byte[1024];//packet array
            byte[] receiveData = new byte[1024];

            String sentence = inFromUser.readLine();// read input from user
            if(sentence.trim() .equals("Q") || sentence.trim().equals("q")){    // if user enters q or Q the program quits
                break;
            }
            sendData = sentence.getBytes();// turn the sentance to a packet array

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, 8080); //send the packet to server

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);// recive the packet from server

            clientSocket.receive(receivePacket);

            String modifiedSentence =
                    new String(receivePacket.getData());//create new string to store recieved packets in

            System.out.println("FROM SERVER:" + modifiedSentence.trim());//print the string out
            clientSocket.close();
        }
    }
}

