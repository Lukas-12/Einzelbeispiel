package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText txt;
    TextView answer;
    String serverString;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=txt.getText().toString();
                PrimeRun primeRun = new PrimeRun(number);
                new Thread(primeRun).start();

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                serverString = primeRun.getMessage();
                Integer newNumber = Integer.parseInt(number);
                String output = newNumber.toString();
                answer.setText(serverString);

            }

            });

    }
}







