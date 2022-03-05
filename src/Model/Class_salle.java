/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Farah BEN HADID
 */
public class Class_salle {
      private int id_salle ;
    private String numero_salle;
    
    

    public  Class_salle(int id_salle, String numero_salle) {
        this.id_salle = id_salle;
        this.numero_salle = numero_salle;
    }

    public Class_salle() {
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public String getNumero_salle() {
        return numero_salle;
    }

    public void setNumero_salle(String numero_salle) {
        this.numero_salle = numero_salle;
    }

    @Override
    public String toString() {
        return "class_salle{" + "id_salle=" + id_salle + ", numero_salle=" + numero_salle + '}';
    }

    
    
    
    
}


