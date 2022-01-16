package com.greendevjr.parolanascosta;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();
    int masterIndex = rand.nextInt(10);

    int attemptsleft = 5;
    int hints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //DATA
        String[] word = {"Parigi", "Acceso", "Carrello", "Gallo", "Giorno", "Orso", "Isola", "Corriere", "Pratica", "Arco"};
        String[] hint = {"Capitale della Francia", "Opposto di spento", "Si usa per fare la spesa", "Il marito della gallina", "Contrario della notte", "Quello polare è bianco", "E' circondata dal mare", "Ti porta dei pacchi sotto casa", "Non è la teoria", "Quello elettrico è luminoso"};

        EditText txinput;
        Button btconfirm, bthint, btnext;
        TextView lblattempts, lblhint, deb, lbltitle, lblfeed;//To-Del(deb)

        txinput = findViewById(R.id.txinput);
        btconfirm = findViewById(R.id.btConfirm);
        bthint = findViewById(R.id.btHint);
        lblattempts = findViewById(R.id.lblAttempts);
        lblhint = findViewById(R.id.lblHint);
        deb = findViewById(R.id.deb);//To-Del
        lbltitle = findViewById(R.id.textView);
        lblfeed = findViewById(R.id.lblFeed);
        btnext = findViewById(R.id.btNext);


        deb.setText(word[masterIndex]);//To-Del
        lblhint.setText("Indizio: "+ hint[masterIndex]);
        lblattempts.setText(String.valueOf(attemptsleft));


        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans = txinput.getText().toString().toLowerCase(Locale.ROOT);
                if (ans.contains(word[masterIndex].toLowerCase(Locale.ROOT))) {
                    lblfeed.setVisibility(View.VISIBLE);
                    lblfeed.setText("CORRETTO!");
                    lblfeed.setTextColor(Color.GREEN);

                    txinput.setEnabled(false);
                    btconfirm.setEnabled(false);
                } else {
                    attemptsleft--;
                    lblattempts.setText(String.valueOf(attemptsleft));
                    txinput.setText("");
                    if (attemptsleft > 0) {
                        lblfeed.setVisibility(View.VISIBLE);
                        lblfeed.setText("SBAGLIATO!");
                        lblfeed.setTextColor(Color.RED);
                    } else {
                        lblfeed.setVisibility(View.VISIBLE);
                        lblfeed.setText("FALLITO!");
                        lblfeed.setTextColor(Color.RED);
                        txinput.setEnabled(false);
                        btconfirm.setEnabled(false);
                        bthint.setEnabled(false);
                    }
                }
            }
        });

        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptsleft = 5;
                hints = 0;
                txinput.setText("");
                txinput.setEnabled(true);
                btconfirm.setEnabled(true);
                lblfeed.setVisibility(View.INVISIBLE);
                lblattempts.setText(String.valueOf(attemptsleft));
                bthint.setEnabled(true);
                masterIndex = rand.nextInt(10);
                deb.setText(word[masterIndex]);//To-Del
                lblhint.setText("Indizio: "+ hint[masterIndex]);
            }
        });

        bthint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hints < word[masterIndex].length()-1) {
                    hints++;
                    txinput.setText(word[masterIndex].substring(0,hints));
                    txinput.clearFocus();

                } else {
                    txinput.setText(word[masterIndex].substring(0,hints+1));
                    lblfeed.setVisibility(View.VISIBLE);
                    lblfeed.setText("FALLITO!");
                    lblfeed.setTextColor(Color.RED);
                    txinput.setEnabled(false);
                    btconfirm.setEnabled(false);
                    bthint.setEnabled(false);
                }

            }
        });

    }
}