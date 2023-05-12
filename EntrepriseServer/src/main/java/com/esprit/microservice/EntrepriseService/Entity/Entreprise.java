package com.esprit.microservice.EntrepriseService.Entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Entreprise implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String nom;

    private String email;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
