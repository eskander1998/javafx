/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author user
 */
public class Class_Reservation_Seance {
    
    private int id;
    private int idseance;
    private int nbplace;
    private String etat;

    public Class_Reservation_Seance() {
    }

    
    public Class_Reservation_Seance(int id, int idseance, int nbplace, String etat) {
        this.id = id;
        this.idseance = idseance;
        this.nbplace = nbplace;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdseance() {
        return idseance;
    }

    public void setIdseance(int idseance) {
        this.idseance = idseance;
    }

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Class_Reservation_Seance{" + "id=" + id + ", idseance=" + idseance + ", nbplace=" + nbplace + ", etat=" + etat + '}';
    }
    
    

}
