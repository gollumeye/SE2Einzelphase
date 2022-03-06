package com.example.einzelphase_laurarandl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button abschickenButton = findViewById(R.id.buttonAbschicken);
        EditText numberInput = findViewById(R.id.editTextNumber);
        TextView ergebnisTextfeld = findViewById(R.id.textViewAnswer);
        abschickenButton.setOnClickListener(e -> {
            String matrikelnummer = numberInput.getText().toString();
            ServerActivity serveractivity = new ServerActivity(matrikelnummer);
            serveractivity.start();
            try{
                serveractivity.join();
                ergebnisTextfeld.setText(serveractivity.getAnswer());
            }
            catch(Exception exception){
                ergebnisTextfeld.setText(serveractivity.getAnswer());
            }
        });
    }



    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button abschickenButton = findViewById(R.id.buttonAbschicken);
        EditText numberInput = findViewById(R.id.editTextNumber);
        TextView ergebnisTextfeld = findViewById(R.id.textView2);
        abschickenButton.setOnClickListener(e -> {
            String matrikelnummer = numberInput.getText().toString();
            try{ergebnisTextfeld.setText(getValueFromServer(matrikelnummer));}
            catch(Exception exception){
                ergebnisTextfeld.setText("Failed to retrieve value from Server");
                System.out.println(exception.getMessage());
            }
        });
    }

    public String getValueFromServer(String matrikelnummer) throws Exception{

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(matrikelnummer + "\n");
        String antwort = inFromServer.readLine();
        clientSocket.close();
        return antwort;

    }

     */

}