/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workout.pkg2;


import Model.Class_Seance;
import Service.SeanceI;
import Util.MaConnexion;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Farah BEN HADID
 */
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNUM;
    @FXML
    private TextField tfNMB;
    @FXML
    private TextField tfC;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
    @FXML
    private ListView lid;
    
    private ObservableList<Class_Seance>List;
    @FXML
    private TextField stat;
    private void handleButtonAction(ActionEvent event) {
        
    }
    ObservableList<Class_Seance> seances;
    SeanceI si=new SeanceI();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateList();
    }    

    public void UpdateList(){
    seances=si.afficher_Seance();
    lid.getItems().setAll(seances);
    }
    
    
    @FXML
    private void supprimer(ActionEvent event) {
        java.sql.Connection cnx = MaConnexion.getInstance().getCnx();  
        try{
            String req="DELETE FROM `seance` WHERE id_seance=?";
               PreparedStatement st=cnx.prepareStatement(req);
               st.setString(1,tfID.getText());
               st.execute();
               JOptionPane.showMessageDialog(null,"seance supprimée!!! ");
        
        
        }
        catch(Exception ex)
        {ex.printStackTrace();}
    }

    @FXML
    private void modifier(ActionEvent event) {
        java.sql.Connection cnx = MaConnexion.getInstance().getCnx();
        
        try{ 
        String value1= tfID.getText();
        String value2=tfNUM.getText();
        String value3=tfNMB.getText();
        String value4=tfC.getText();
        String req="UPDATE `seance` SET `id_seance`='"+value1+"',`nom_seance`='"+value2+"',`heure_seance`='"+value3+"',`coach`='"+value1+"'";
        PreparedStatement st=cnx.prepareStatement(req);
        st.execute();
        JOptionPane.showMessageDialog(null,"seance bien modifiée ");
        UpdateList();
        }
        catch(Exception ex){
       ex.printStackTrace();
       }
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        java.sql.Connection cnx = MaConnexion.getInstance().getCnx();
        String req="INSERT INTO `seance`(`nom_seance`, `heure_seance`, `coach`) VALUES (?,?,?)";
        try{ PreparedStatement st=cnx.prepareStatement(req);
        st.setString(1,tfNUM.getText());
        st.setString(2,tfNMB.getText());
        st.setString(3,tfC.getText());
        st.execute();
        JOptionPane.showMessageDialog(null,"seance bien ajoutée ");
        UpdateList();
        
        }
        catch(Exception ex){
        ex.printStackTrace();}
    }

    @FXML
    private void stat(ActionEvent event) {
        
                java.sql.Connection cnx = MaConnexion.getInstance().getCnx();  


            List = FXCollections.observableArrayList();

            try {

                ResultSet rs = cnx.createStatement().executeQuery("SELECT AVG(id_seance='1') As t FROM seance");

                while (rs.next()) {

                    float s = rs.getFloat("t") * 100;
                    String s1 = Float.toString(s);
                    stat.setText(s1 + " %");

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

         
    }
    
}
