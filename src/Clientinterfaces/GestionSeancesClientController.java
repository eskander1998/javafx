/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Model.Class_Reservation_Seance;
import Service.Reservation_SeanceI;
import Util.MaConnexion;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionSeancesClientController implements Initializable {

    @FXML
    private Label idreserv;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton btngestionreservation;
    @FXML
    private JFXButton btngoback;
    @FXML
    private JFXButton btnGoToseance;
    @FXML
    private JFXButton btngestionSeances;

             private MyListenerReservS myListener;

         private Statement ste;
     MaConnexion db=new MaConnexion();
     private Connection con=db.getCnx();
     
     Reservation_SeanceI cs = new Reservation_SeanceI();

    List<Class_Reservation_Seance> reservationss = cs.afficher_Reservation();
    
    /**
     * Initializes the controller class.
     */
    
    
    private List<Class_Reservation_Seance> getData() {
    List<Class_Reservation_Seance> reservationss = cs.afficher_Reservation();
    
          ObservableList<Class_Reservation_Seance> data =
                  
        FXCollections.observableArrayList(reservationss );

     
        return data;
    }
    

    
    private void setChosenResrvationSeance(Class_Reservation_Seance e) {
        String id =String.valueOf(e.getId());
//        idOrganisateurLabel.setText(idOrga);
        idreserv.setText(id);
       

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
     
        
        if (reservationss.size() > 0) {
            setChosenResrvationSeance(reservationss.get(0));
            myListener = new MyListenerReservS() {
                public void onClickListener(Class_Reservation_Seance e) {
                    setChosenResrvationSeance(e);
                }


            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reservationss.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemReservation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

               ItemReservationController ItemReservationController = fxmlLoader.getController();
                ItemReservationController.setData(reservationss.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void gotoseance(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherSeanceClient.fxml"));
  
            Parent root = loader.load();
            btnGoToseance.getScene().setRoot(root);   
    }
    @FXML
    private void gotogestionseance(MouseEvent event) {
    }
    
}
