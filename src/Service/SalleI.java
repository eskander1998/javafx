/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interfaces.salle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Class_Seance;
import Model.Class_salle;
import Util.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Farah BEN HADID
 */ 
public class SalleI implements salle{
    
    java.sql.Connection cnx = MaConnexion.getInstance().getCnx();

    public ObservableList<Class_salle> afficher_salle() {
        //var
        ObservableList<Class_salle> salles = FXCollections.observableArrayList();
        //request
        String req = "SELECT * FROM salle";

        try {
            //exec

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                salles.add(new Class_salle(rs.getInt(1), rs.getString(2))); //thabet ml BD
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salles;
    }

   
}

    

    
    
    


