package cloud.voiture.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Document(collection = "annonce")
public class Annonce {

    @Id
    private String id;
    private Marque marque;
    private Categorie categorie;
    private Types type;
    private double prix;
    private Date date;
    private Utilisateur utilisateur;
    private int etat;

    public Annonce(String id, Marque marque, Categorie categorie, Types type, double prix, Date date,
    Utilisateur utilisateur, int etat) {
        this.id = id;
        this.marque = marque;
        this.categorie = categorie;
        this.type = type;
        this.prix = prix;
        this.date = date;
        this.utilisateur = utilisateur;
        this.etat = etat;
    }



    public Annonce() {
    }

  

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Marque getMarque() {
        return marque;
    }
    public void setMarque(Marque marque) {
        this.marque = marque;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public Types getType() {
        return type;
    }
    public void setType(Types type) {
        this.type = type;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
  
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }



    public Utilisateur getUtilisateur() {
        return utilisateur;
    }



    public void setIdutilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

   
}
