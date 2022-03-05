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
import java.sql.PreparedStatement;
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

     @Override
    public void ajoutersalle(Class_salle s) {
       
         String req=" INSERT INTO `salle`(`id_salle`, `numero_salle`) VALUES ('"+s.getId_salle()+"','"+s.getNumero_salle()+"')";
           try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("salle ajouter avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
        
    }

    @Override
    public List<Class_salle> affichersalle() {
           List<Class_salle> salles=new ArrayList<>();
      String req="SELECT * FROM salle";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 salles.add(new Class_salle(rs.getInt(1), rs.getString(2)));
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
        
    }
      return salles;
    }
 @Override
     public void DeleteClasse(int a) {
        try {
            String requete = "delete from salle where ? = id_salle";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a);
            pst.executeUpdate();
            System.out.println("salle Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
   

    @Override
public void modifiersalle (Class_salle e) {
    
        try {
            String requete = "update salle set numero_salle=? where ? = id_salle";
            PreparedStatement pre = cnx.prepareStatement(requete);
            pre.setString(1, e.getNumero_salle());
            pre.setInt(2,e.getId_salle());
            pre.executeUpdate();
            System.out.println("salle Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   
}

    

    
    
    


