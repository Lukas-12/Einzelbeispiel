package com.example.einzelbeispiel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Class to connect to a Server
 * With input and output
 */
public class ServerConnection extends Thread implements Runnable {
    String matNr;
    String message;

    ServerConnection(String matNr){
        this.matNr = matNr;
    }


    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(matNr +'\n');
            message = inFromServer.readLine();
            clientSocket.close();
        }catch (Exception e){
            e.getMessage();
        }
    }
    public String getMessage() {

        return message;
    }

    public String getMatNr() {
        return matNr;
    }
}
