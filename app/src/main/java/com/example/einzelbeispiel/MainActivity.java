package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText txt;
    TextView answer;
    String serverString;
    Button btnCalc;
    String matNr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }
    public void addListenerOnButton() {
        btn = findViewById(R.id.Abschicken);
        txt = findViewById(R.id.eingabe);
        answer = findViewById(R.id.serverAntwort);
        btnCalc = findViewById(R.id.Berrechnen);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matNr=txt.getText().toString();
                ServerConnection primeRun = new ServerConnection(matNr);
                new Thread(primeRun).start();

                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                serverString = primeRun.getMessage();
                answer.setText(serverString);
                if(serverString.equals("Dies ist keine gueltige Matrikelnummer") ){
                    matNr = "-1";
                }

            }

            });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matNr == null){
                    answer.setText("Bitte zuerst auf Abschicken klicken!");
                }
                else if(matNr.contains("-1")){
                    answer.setText("Bitte gueltige Matrikelnummer eingeben");
                }
                else{
                    String newNumber = calc(matNr);
                    answer.setText(newNumber);
                }


            }

        });


    }
    public String calc(String toCalc){
        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < toCalc.length(); i++) {
            if(toCalc.charAt(i) % 2 == 0){
                even.append(toCalc.charAt(i));
            }
            else{
                odd.append(toCalc.charAt(i));
            }
        }
        char[] one = even.toString().toCharArray();
        char[] two = odd.toString().toCharArray();
        one = sort(one);
        two = sort(two);
        result.append(one);
        result.append(two);
        return result.toString();

    }
    public char[] sort(char[] toSort){
        char swap;

        for(int i=1; i<toSort.length; i++) {
            for(int j=0; j<toSort.length-i; j++) {
                if(toSort[j]>toSort[j+1]) {
                    swap=toSort[j];
                    toSort[j]=toSort[j+1];
                    toSort[j+1]=swap;
                }
            }
        }
        return  toSort;
    }
}







