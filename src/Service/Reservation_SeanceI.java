/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interfaces.ReservationSeance;
import Model.Class_Reservation_Seance;
import Model.Class_Seance;
import Util.MaConnexion;
import Util.sqlexcept;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class Reservation_SeanceI implements ReservationSeance {    //la classe doit implementer de l'interface ses méthodes
    //var 
    static java.sql.Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public ObservableList<Class_Reservation_Seance> afficher_Reservation() {
        
   ObservableList<Class_Reservation_Seance> reservations = FXCollections.observableArrayList();
        //request
        String req = "SELECT * FROM reservation_seance";

        try {
            //exec
           
            
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                reservations.add(new Class_Reservation_Seance(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4))); //thabet ml BD
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservations;    }
    
    
    public void DeleteClasse(int a) {
        try {
            String requete = "delete from reservation_seance where ? = id";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a);
            pst.executeUpdate();
            System.out.println("reservation_seance Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void ajouter1(Class_Reservation_Seance e) throws IOException,sqlexcept
    {
    PreparedStatement pre;
        try {
           
            pre = cnx.prepareStatement("INSERT INTO  `reservation_seance`(`id`, `idseance`, `nbplaces`, `etat`) VALUES (?, ?, ?,?);");
            pre.setInt(1, e.getId());
            pre.setInt(2, e.getIdseance());
            pre.setInt(3, e.getNbplace());
            pre.setString(4, e.getEtat());
            

    pre.executeUpdate();
        System.out.println("Réservation à la séance ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
 
    }
    
   
}