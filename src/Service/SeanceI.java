/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interfaces.seance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Class_Seance;
import static Service.Reservation_SeanceI.cnx;
import Util.MaConnexion;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Farah BEN HADID
 */
public class SeanceI implements seance {    //la classe doit implementer de l'interface ses méthodes
    //var 
    static java.sql.Connection cnx = MaConnexion.getInstance().getCnx();
    
    
    public ObservableList<Class_Seance> afficher_Seance() {
          //var
        ObservableList<Class_Seance> seances =FXCollections.observableArrayList();
        //request
        String req = "SELECT * FROM seance";

        try {
            //exec

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                seances.add(new Class_Seance(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10))); //thabet ml BD
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seances;
    }
    
    public ObservableList<Class_Seance> afficher_SeanceByid(int id ) {
          //var
        ObservableList<Class_Seance> seances =FXCollections.observableArrayList();
        //request
        String req = "SELECT * FROM seance where ? = id_seance ";

        try {
            //exec
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,id);            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                seances.add(new Class_Seance(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10))); //thabet ml BD
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seances;
    } 
    
    
      public int count_nbplace(int id_seance) throws SQLException
    {
         String requete = "SELECT SUM(nbplaces) AS nbplace_total FROM reservation_seance WHERE ? =idseance";
            PreparedStatement p = cnx.prepareStatement(requete);
            p.setInt(1, id_seance);
            
            int result=0;

            ResultSet ee = p.executeQuery();
            ee.next();
            
            result=ee.getInt(1);
            ee.close();
            p.close();
            
            return result;
    }
  
public  List < String > calculdate() {
        List<String> arr = new ArrayList<>();
            String requete = "SELECT distinct date from seance order by date asc";
try{
          PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String date = rs.getDate("date").toString();
                arr.add(date);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }
   
}
