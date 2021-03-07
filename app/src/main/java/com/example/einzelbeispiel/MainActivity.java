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
        btnCalc = findViewById(R.id.Berechnen);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * Button "Abschicken"
             * Button connect to the Server
             * Checks the input number and prints a message
             */
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
            /**
             * Button "Berechnen"
             * Check if it is possible to click on the "Berechnen" button
             * If its possible a String with an new Number will be printed
             */
            public void onClick(View view) {
                if(matNr == null){
                    answer.setText("Bitte zuerst auf Abschicken klicken!");
                }
                else if(matNr.contains("-1")){
                    answer.setText("Bitte gueltige Matrikelnummer eingeben");
                    matNr = null;
                }
                else{
                    String newNumber = calc(matNr);
                    answer.setText(newNumber);
                    matNr = null;
                }


            }

        });


    }

    /**
     *  Method who sort a number.
     *  First even then odd and sorted from low to high.
     *
     * @param toCalc: Original Matrikelnummer
     * @return result: A String with even and odd Sorted
     */
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
        result.append(sort(one));
        result.append(sort(two));
        return result.toString();

    }

    /**
     * Classic BubbleSort
     * @param toSort: a Char Array with Digits
     * @return toSort: Sorted Char Array
     */
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







