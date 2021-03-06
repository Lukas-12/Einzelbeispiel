package com.example.einzelbeispiel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args)throws Exception {
        int clientMatNr;

        Socket clientSocket = new Socket("se2-isys.aau.at",53212);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        clientMatNr = inFromServer.read();
        outToServer.writeByte(clientMatNr);
        System.out.println("FROM SERVER: " + clientMatNr);
        clientSocket.close();

    }
}