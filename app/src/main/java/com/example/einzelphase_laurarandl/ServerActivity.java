package com.example.einzelphase_laurarandl;
import java.io.*;
import java.net.*;

public class ServerActivity extends Thread {
    String input;
    String answer;

    public String getAnswer() {
        return answer;
    }

    ServerActivity(String matrikelnummer){
        this.input = matrikelnummer;
    }

    @Override
    public void run(){
        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(input + "\n");
            this.answer = inFromServer.readLine();
            clientSocket.close();
        }
        catch(Exception exception){
            this.answer = "Error occurred during communication with server!";
        }
    }
}
