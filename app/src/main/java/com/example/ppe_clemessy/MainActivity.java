package com.example.ppe_clemessy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button boutonconnexion;
    private Button boutoninsription;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.boutonconnexion = findViewById(R.id.boutonConnexion);
        boutonconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent connexion = new Intent(getApplicationContext(),Connexion.class);
                startActivity(connexion);
            }
        });

        this.boutoninsription = findViewById(R.id.boutonInscription);
        boutoninsription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inscription = new Intent(getApplicationContext(),Inscription.class);
                startActivity(inscription);
            }
        });




    }
}