/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Model.Class_Seance;
import Service.Reservation_SeanceI;
import Service.SeanceI;
import Util.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemClassSeanceController {
 public static int place_reste= 0;

    @FXML
    private ImageView img;
    @FXML
    private Label nameLabel;
    private Label priceLable;
    @FXML
    private Label dateSeance;
    @FXML
    private Label etatLabel;

     MaConnexion db=new MaConnexion();
               private Connection con=db.getCnx();
    @FXML
    private Button btnLirePlus;
    @FXML
    private Label lieuLable;
    @FXML
    private Label idLabel;
    @FXML
    private Label heureLabel;
    @FXML
    private Label coachLabel;
    @FXML
    private Label capaciteLabel;
    @FXML
    private Label nbreservationLabel;
    @FXML
    private Label imageLabel;
    @FXML
    private Label idsalleLabel;
      
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(seance);
    }

    private Class_Seance seance;
    private MyListener myListener;

    public void setData(Class_Seance seance, MyListener myListener) {
        
        this.seance = seance;
        
        Date d=seance.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");  
        String strDate = dateFormat.format(d);
        
        this.myListener = myListener;
        nameLabel.setText(seance.getNom());
        lieuLable.setText(String.valueOf( seance.getIdsalle()) );
        dateSeance.setText(strDate);
        etatLabel.setText(seance.getEtat());
        
        idLabel.setText(String.valueOf(seance.getId()));
        heureLabel.setText(String.valueOf(seance.getHeure()));
        System.out.println("heureeeeeeee : " + heureLabel.getText());
        coachLabel.setText(seance.getCoach());
        capaciteLabel.setText(String.valueOf(seance.getCapacite()));
        nbreservationLabel.setText(String.valueOf(seance.getNbreservation()));        
        imageLabel.setText(seance.getImage());        
                
        String imgg= seance.getImage();
        String ch="/imgSeance/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
    }

    @FXML
    private void LirePlus_Reserver(MouseEvent event) throws SQLException {
        
        String titre=  nameLabel.getText();
        String LieuSeance= lieuLable.getText();
        String DateSeance= dateSeance.getText();
        String ImagSeance= (imageLabel.getText());
        String EtatSeance= (etatLabel.getText());
        String IdSeance= idLabel.getText();
        String HeureSeance= heureLabel.getText();
        String CoachSeance= coachLabel.getText();
        String CpaciteSeance= capaciteLabel.getText();
        String nbresSeance= nbreservationLabel.getText();

        String ch="/imgSeance/";
        String imgF= ch+ImagSeance;
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        int a =1;
        
         SeanceI cs = new SeanceI();
         int place_reste= Integer. parseInt(CpaciteSeance)- cs.count_nbplace(Integer.parseInt(IdSeance));

    if(a==1){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverSeance.fxml"));
        try {
            Parent root = loader.load();
            ReserverSeanceController pdc = loader.getController();
            pdc.setNomSeanceLbale(titre);
            pdc.setImgSeance(imageF);
            pdc.setDateSeanceLabel(DateSeance);
//            pdc.setOrganisateurEventLabel(IdOrganisateur);
            pdc.setLieuSeanceLabel(LieuSeance);
            pdc.setIdSeanceLabel(IdSeance);
            pdc.setCoachLabel(CoachSeance);
            pdc.setPlaceDsipoLabel(String.valueOf(place_reste));
            pdc.setCapcitreLabel(CpaciteSeance);
            pdc.setHeureSeanceLabel(HeureSeance);
            btnLirePlus.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ItemClassSeanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
