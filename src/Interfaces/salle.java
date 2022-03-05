/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javafx.collections.ObservableList;
import Model.Class_salle;
import java.util.List;
/**
 *
 * @author Farah BEN HADID
 */
public interface salle {
    
     

    //select
   public ObservableList<Class_salle> afficher_salle();
    

         public void modifiersalle (Class_salle e) ;
     public void DeleteClasse(int a) ;
    public List<Class_salle> affichersalle() ;
    public void ajoutersalle(Class_salle s) ;

    
}
