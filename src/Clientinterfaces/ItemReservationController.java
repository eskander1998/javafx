/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Model.Class_Reservation_Seance;
import Model.Class_Seance;
import Service.Reservation_SeanceI;
import Service.SeanceI;
import Util.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemReservationController implements Initializable {

    @FXML
    private Label lieuLable;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label dateSeance;
    @FXML
    private Label etatLabel;
    @FXML
    private Label coachLabel;
    @FXML
    private Label heureSeance;
    @FXML
    private Label idLabel;
    @FXML
    private Label idReservation;
    @FXML
    private Label heureLabel;
    @FXML
    private Label capaciteLabel;
    @FXML
    private Label nbreservationLabel;
    @FXML
    private Label imageLabel;
    @FXML
    private Label idsalleLabel;
    @FXML
    private Button btnAnnuler;

     MaConnexion db=new MaConnexion();
               private Connection con=db.getCnx();
               
                 private Class_Reservation_Seance reservation;
    private MyListenerReservS myListener;
    
    
    
     public void setData(Class_Reservation_Seance reservation, MyListenerReservS myListener) {
        
        this.reservation = reservation;
        
        SeanceI cs = new SeanceI();
    List<Class_Seance> seancess = cs.afficher_Seance();
        

        idReservation.setText(String.valueOf(reservation.getId()));
         
      for (int i = 0; i < seancess.size(); i++) {

             if(seancess.get(i).getId()== reservation.getIdseance())
             {
                              System.out.println("iddd : "+seancess.get(i).getNom());
  
        Date d=seancess.get(i).getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");  
        String strDate = dateFormat.format(d);
        
        this.myListener = myListener;
        nameLabel.setText(seancess.get(i).getNom());
        lieuLable.setText(String.valueOf( seancess.get(i).getIdsalle()) );
        dateSeance.setText(strDate);
        etatLabel.setText(seancess.get(i).getEtat());
        
        idLabel.setText(String.valueOf(seancess.get(i).getId()));
        heureLabel.setText(String.valueOf(seancess.get(i).getHeure()));
        System.out.println("heureeeeeeee : " + heureLabel.getText());
        coachLabel.setText(seancess.get(i).getCoach());
        capaciteLabel.setText(String.valueOf(seancess.get(i).getCapacite()));
        nbreservationLabel.setText(String.valueOf(seancess.get(i).getNbreservation()));        
        imageLabel.setText(seancess.get(i).getImage());        
                
        String imgg= seancess.get(i).getImage();
        String ch="/imgSeance/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
                 
             }
         }
    
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AnnulerRes(MouseEvent event) {
        
                Reservation_SeanceI cs = new Reservation_SeanceI();
        
                
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Annulation la réservation ");
            alert.setHeaderText("Voulez vous annuler cette réservation");
            alert.setContentText("Confirmez l'anulation");
                        Optional<ButtonType> btn = alert.showAndWait();

             if(btn.get()==ButtonType.OK )
            {
                            cs.DeleteClasse(Integer.parseInt(idReservation.getText()));

            }
    }

    @FXML
    private void click(MouseEvent event) {
    }
    
}
