package com.example.einzelphase_laurarandl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
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

        Button berechnenButton = findViewById(R.id.buttonBerechnen);
        berechnenButton.setOnClickListener(e -> {
            String matrikelnummer = numberInput.getText().toString();
            if(!matrikelnummer.equals("")) {
                if (pruefeObAlternierendeQuersummeGerade(matrikelnummer)) {
                    ergebnisTextfeld.setText("Die Alternierende Quersumme ist gerade");
                } else {
                    ergebnisTextfeld.setText("Die Alternierende Quersumme ist ungerade");
                }
            }
            else{
                ergebnisTextfeld.setText("Bitte gib eine Matrikelnummer ein");
            }
        });

    }


    public boolean pruefeObAlternierendeQuersummeGerade(String matrikelnummer){

        int quersumme = 0;

        for(int i = 0; i<matrikelnummer.length(); i++){
            if(i%2==0){
                quersumme+= (int) matrikelnummer.charAt(i);
            }
            else{
                quersumme-=(int) matrikelnummer.charAt(i);
            }
        }

        if(quersumme%2==0){
            return true;
        }

        return false;
    }
}