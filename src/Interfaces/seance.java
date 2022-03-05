/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;



import Model.Class_Seance;
import javafx.collections.ObservableList;


/**
 *
 * @author Farah BEN HADID
 */
public interface seance {
    
    


    public void ajouterseance(Class_Seance s) ;  
    public ObservableList<Class_Seance> afficher_Seance();
     public void DeleteClasse(int a) ;
    public void modifierseance(Class_Seance s) ;  
     
}

