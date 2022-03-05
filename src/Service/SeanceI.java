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
import java.util.Calendar;
import java.util.Date;
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
    
    
       public List<Class_Seance> afficherseance() {
           List<Class_Seance> seances=new ArrayList<>();
       try {
            String requete = "SELECT now(),`id_seance`,`nom_seance`,`heure_seance`,`coach`,`date`,`capacite`,`image`,`nbreservation`,`etat`,`idsalle` FROM `seance`  order by id_seance  desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            
            while (e.next()) {
                Class_Seance pre = new Class_Seance();
               
            pre.setId(e.getInt("id_seance"));
            pre.setNom(e.getString("nom_seance"));

            pre.setCapacite(e.getInt("capacite"));
            pre.setHeure(e.getInt("heure_seance"));
            pre.setCoach(e.getString("coach"));
            pre.setDate(e.getDate("date"));
            pre.setEtat(e.getString("etat"));
            pre.setIdsalle(e.getInt("idsalle"));
            pre.setNbreservation(e.getInt("nbreservation"));
            pre.setImage(e.getString("image"));
            pre.setEtat(e.getString("etat"));
             
                int reserv = count_nbplace(pre);
                
            pre.setNbreservation(reserv);
            modifier_nb_reservation(pre,reserv);

 
            Date date_event= e.getDate("date");
            int hr= e.getInt("heure_seance");

            Date jour = e.getDate("now()");
        Calendar rightNow = Calendar.getInstance();
int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            if(jour.before(date_event)|| (jour.equals(date_event)&&(hour<hr))){

                pre.setEtat("en cours aa");
                                modifier_etat2(pre);

                System.out.println("1");
            }
          
            else {
                
                  modifier_etat(pre);
                System.out.println("2");
            
                  pre.setEtat("effectue");

            }
                seances.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
    
    
    
         public void modifier_etat(Class_Seance r) 
    {

             try {
         String requete = "update `seance`  set etat='effectue' where (? = id_seance and  (DATEDIFF( ?,NOW()) )<=0 )";
            PreparedStatement pre = cnx.prepareStatement(requete);
                       pre.setInt(1, r.getId());
                       pre.setDate(2, (java.sql.Date) r.getDate());
            pre.executeUpdate();
//            System.out.println("etat modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
              public void modifier_etat2(Class_Seance r) 
    {

             try {
         String requete = "update `seance`  set etat='en cours' where ? = id_seance ";
            PreparedStatement pre = cnx.prepareStatement(requete);
                       pre.setInt(1, r.getId());
            pre.executeUpdate();
//            System.out.println("etat modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
        public void modifier_nb_reservation(Class_Seance r,int nbr) 
    {
             try {
            String requete = "update `seance`  set nbreservation=? where ? = id_seance ";
            PreparedStatement pre = cnx.prepareStatement(requete);
            pre.setInt(1,nbr);           
            pre.setInt(2, r.getId());
            pre.executeUpdate();
//            System.out.println("etat modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
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
  
      
  public int count_nbplace(Class_Seance e) throws SQLException
    {
         String requete = "SELECT SUM(nbplaces) AS nbplace_total FROM reservation_seance WHERE ? =idseance";
            PreparedStatement p = cnx.prepareStatement(requete);
            p.setInt(1, e.getId());
            
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

    @Override
    public void ajouterseance(Class_Seance s) {
       
         String req=" INSERT INTO `seance`(`id_seance`, `nom_seance`,  `heure_seance`, `coach`, `date`,`capacite`,`nbreservation`,`image`,`etat`,`idsalle`) VALUES ('"+s.getId()+"','"+s.getNom()+"','"+s.getHeure()+"','"+s.getCoach()+"','"+new java.sql.Date(s.getDate().getTime())+"','"+s.getCapacite()+"','"+s.getNbreservation()+"','"+s.getImage()+"','"+"en cours"+"','"+s.getIdsalle()+"')";
           try {
             //insert
             Statement st=cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("seance ajouter avec succes");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
        
    }
    
  @Override
     public void DeleteClasse(int a) {
        try {
            String requete = "delete from seance where ? = id_seance";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a);
            pst.executeUpdate();
            System.out.println("seance Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

   @Override
    public void modifierseance(Class_Seance e) {
       
        try {
            String requete = "update seance set nom_seance=?,heure_seance=?,coach=?,date=?,capacite=?,image=?,idsalle=? where ? = id_seance";
            PreparedStatement pre = cnx.prepareStatement(requete);
            pre.setString(1, e.getNom());
            pre.setInt(2, e.getHeure());
            pre.setString(3, e.getCoach());
                        pre.setDate(4, new java.sql.Date(e.getDate().getTime()));
            pre.setInt(5,e.getCapacite());
            pre.setString(6, e.getImage());
            pre.setInt(7,e.getIdsalle());
            pre.setInt(8,e.getId());
            pre.executeUpdate();
            System.out.println("seance Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   

    
    
}
