package com.example.ppe_clemessy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Création de la table des utilisateurs
@Entity(tableName = "utilisateurs")
public class User {

    //Clé primaire avec incrémentations automatique
    @PrimaryKey(autoGenerate = true)
    public int uid;
    //Les noms des différentes colonnes et leurs attributs
    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "prenom")
    public String prenom;

    @ColumnInfo(name = "mot de passe")
    public String motDePasse;

    //Constructeur pour aller chercher les différentes données
    public User(int uid, String leNom, String lePrenom, String leMdp) {
        this.uid = uid;
        this.nom = leNom;
        this.prenom = lePrenom;
        this.motDePasse = leMdp;
    }
    // Création du contructeur vide pour que la UserDAO_impl arrete de pété
    public User() {

    }

    // Mise en place des différents Getter et Setter

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
