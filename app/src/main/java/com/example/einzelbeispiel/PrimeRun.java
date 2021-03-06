package com.example.einzelbeispiel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class PrimeRun implements Runnable {

    public String getMatNr() {
        return matNr;
    }

    String matNr;

    PrimeRun(String matNr){
        this.matNr = matNr;
    }


    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            matNr = inFromServer.readLine();
            outToServer.writeBytes(matNr);
            clientSocket.close();
        }catch (Exception e){
            e.getMessage();
        }
    }
}
