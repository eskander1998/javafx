/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Class_Reservation_Seance;
import Model.Class_salle;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public interface ReservationSeance {
    
       public ObservableList<Class_Reservation_Seance> afficher_Reservation();

}
