/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Farah BEN HADID
 */
public class Class_Seance {
    
    private int id;
    private String nom;
    private int heure;
    private String coach;
    private Date date;
    private int capacite;
    private int nbreservation;
    private String image;
    private String etat;
    private int idsalle;

    public Class_Seance(int id, String nom, int heure, String coach, Date date, int capacite, int nbreservation, String image, String etat, int idsalle) {
        this.id = id;
        this.nom = nom;
        this.heure = heure;
        this.coach = coach;
        this.date = date;
        this.capacite = capacite;
        this.nbreservation = nbreservation;
        this.image = image;
        this.etat = etat;
        this.idsalle = idsalle;
    }

    public Class_Seance() {
    }
    
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

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNbreservation() {
        return nbreservation;
    }

    public void setNbreservation(int nbreservation) {
        this.nbreservation = nbreservation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(int idsalle) {
        this.idsalle = idsalle;
    }
    

    
    
}