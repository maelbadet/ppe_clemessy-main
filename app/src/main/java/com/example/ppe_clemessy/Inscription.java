package com.example.ppe_clemessy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Inscription extends AppCompatActivity {

    private Button precedent;
    private CheckBox affichemdp;
    private Button inscription;
    private EditText nom;
    private EditText prenom;
    private EditText mdp;

    private String JSON_URL = "http://localhost/adminer.php?pgsql=localhost&username=mael&db=CLEMESSY&ns=public";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

    this.precedent = findViewById(R.id.retours);
    this.inscription = findViewById(R.id.inscription_bouton);
    this.nom = findViewById(R.id.connexion_nom);
    this.prenom = findViewById(R.id.prenom);
    this.mdp = findViewById(R.id.connexion_mdp);

        ArrayList<User> utilisateurs = new ArrayList<User>();



        //retours a la page précédente
        precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retours = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(retours);
                finish();
            }
        });


    /*===================================================================*/
    /*===============Clique sur le bouton inscription====================*/
    /*===================================================================*/
    inscription.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id= 0;
            String leNom ="";
            String lePrenom = "";
            String leMdp = "";
            Boolean validite = true;
            if(nom.getText().toString().trim().equals("")==false && validite == true){
                leNom=nom.getText().toString().trim();
            }else{
                validite=false;
            }
            if(prenom.getText().toString().trim().equals("")==false && validite == true){
                lePrenom=prenom.getText().toString().trim();
            }else{
                validite=false;
            }
            if(mdp.getText().toString().trim().equals("")==false && validite == true){
                leMdp=mdp.getText().toString().trim();
            }else{
                validite=false;
            }
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            if (validite==false){
                Toast.makeText(getApplicationContext(),"Il manque des informations",Toast.LENGTH_SHORT).show();
                leNom = null;
                lePrenom = null;
                leMdp = null;
            }

            Log.i("mael", "on rentre dans la validité");
            if (validite==true){

                Log.i("mael", "message validé");


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject jsonObjectRequest) {
                            try {
                                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDatabase.class, "les films").allowMainThreadQueries().build();
                                UserDAO userDAO = db.userDAO();
                                Log.i("mael","avant Json");
                                //Réupération des différents éléments de l'api en bouclant sur les différents id de résults
                                JSONArray jsonArray = jsonObjectRequest.getJSONArray("results");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Log.i("mael","boucle for instancier");
                                    JSONObject utilisateurs = jsonArray.getJSONObject(i);
                                    Log.i("mael","recup utilisateur" + utilisateurs);
                                    String nom = utilisateurs.getString("nom");
                                    String prenom = utilisateurs.getString("prenom");
                                    String mdp = utilisateurs.getString("motdepasse");
                                    Log.i("mael","on affiche les noms,prenoms,mdps");
                                    User lesfilms = new User(utilisateurs.getInt("id"),nom,prenom,mdp);
                                    //insertion dans la base de données Room
                                    userDAO.insertAll(lesfilms);


                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Erreur de connexion au serveur",
                            Toast.LENGTH_SHORT).show();
                }
            });


        }
        }
    });

        /*===================================================================*/
        /*============fin du Clique sur le bouton inscription=================*/
        /*===================================================================*/

        /*===================================================================*/
        /*============Clique sur le bouton affiche mot de passe==============*/
        /*===================================================================*/

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

        /*===================================================================*/
        /*========fin du Clique sur le bouton affiche mot de passe===========*/
        /*===================================================================*/

    }



}