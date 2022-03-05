/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Model.Class_Reservation_Seance;
import Service.Reservation_SeanceI;
import Service.SeanceI;
import Util.sqlexcept;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReserverSeanceController implements Initializable {

    @FXML
    private Label nomSeanceLbale;
    @FXML
    private ImageView imgSeance;
    @FXML
    private Label DateSeanceLabel;
    @FXML
    private Label lieuSeanceLabel;
    @FXML
    private Label idSeanceLabel;
    @FXML
    private JFXButton btnReserverSeance;
    @FXML
    private Label coachLabel;
    @FXML
    private Label HeureSeanceLabel;
    @FXML
    private Label PlaceDsipoLabel;
    @FXML
    private Label capcitreLabel;
    @FXML
    private JFXButton btngestionreservation;
    @FXML
    private JFXButton btngoback;
    @FXML
    private JFXButton btngestionSeances;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton btnGoToSeances;

    public void setNomSeanceLbale(String nomSeanceLbale) {
        this.nomSeanceLbale .setText( nomSeanceLbale);
    }

    public void setImgSeance(Image imgSeance) {
        this.imgSeance .setImage(imgSeance);
    }

    public void setDateSeanceLabel(String DateSeanceLabel) {
        this.DateSeanceLabel .setText( DateSeanceLabel);
    }

    public void setLieuSeanceLabel(String lieuSeanceLabel) {
        this.lieuSeanceLabel .setText( lieuSeanceLabel);
    }

    public void setIdSeanceLabel(String idSeanceLabel) {
        this.idSeanceLabel .setText( idSeanceLabel);
    }

    public void setCoachLabel(String coachLabel) {
        this.coachLabel .setText( coachLabel);
    }

    public void setHeureSeanceLabel(String HeureSeanceLabel) {
        this.HeureSeanceLabel .setText(HeureSeanceLabel);
    }

    public void setPlaceDsipoLabel(String PlaceDsipoLabel) {
        this.PlaceDsipoLabel .setText( PlaceDsipoLabel);
    }

    public void setCapcitreLabel(String capcitreLabel) {
        this.capcitreLabel .setText( capcitreLabel);
    }

    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void gotoevent(MouseEvent event) {
    }

    @FXML
    private void gotogestionreservation(MouseEvent event) {
    }

    @FXML
    private void gotoback(MouseEvent event) {
    }



    @FXML
    private void AjouterReservationSeance(ActionEvent event) throws IOException, sqlexcept {
        
        
            String idseance =idSeanceLabel.getText();

           
            Class_Reservation_Seance c = new Class_Reservation_Seance(0,Integer.parseInt(idseance) ,1,"en cours");
            Reservation_SeanceI cs = new Reservation_SeanceI();
            
            
            cs.ajouter1(c);
      
     
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Bravo");
               alert.setHeaderText("Vous êtes inscrit à la séance  :" + nomSeanceLbale.getText());
               alert.setContentText(" Soyez au rendez vous le "+ DateSeanceLabel.getText() +" dans la salle "+ lieuSeanceLabel.getText());
               alert.showAndWait();

 
                      }

    @FXML
    private void gotoseance(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherSeanceClient.fxml"));
  
            Parent root = loader.load();
            btnGoToSeances.getScene().setRoot(root);   
    }
                      
    }
    

