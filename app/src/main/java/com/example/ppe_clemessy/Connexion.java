package com.example.ppe_clemessy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Connexion extends AppCompatActivity {

    // déclaration des différentes variables pour les vues
    private Button connexion;
    private Button inscription;
    private Button retour;
    private CheckBox affichemdp;
    private EditText nom;
    private EditText mdp;
    private CheckBox rester_co;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        /*========================================================================*/
        /*===============Mise en place des redirections des boutons===============*/
        /*========================================================================*/

        //redirection vers la page principal avec le bouton précédent
        this.retour = findViewById(R.id.retours);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retours = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(retours);
                finish();
            }
        });


        //redirection vers mla page d'inscription.
        this.inscription = findViewById(R.id.redirection_inscription);
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redir_inscription = new Intent(getApplicationContext(), Inscription.class);
                startActivity(redir_inscription);
                finish();
            }
        });

        /*========================================================================*/
        /*==================fin des redirections des boutons======================*/
        /*========================================================================*/


    this.affichemdp = findViewById(R.id.affichemdp);
    affichemdp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox)view).isChecked();
            if (checked){
                // afficehr le mot de passe en claire si la checkbox est cochée
                mdp.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                // afficher le code en masquer si la checkbox est déchoché.
                mdp.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    });

    }
}