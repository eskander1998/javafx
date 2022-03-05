/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backinterfaces;

import Model.Class_Reservation_Seance;
import Model.Class_Seance;
import Service.Reservation_SeanceI;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionReservationSeanceBackController implements Initializable {

    @FXML
    private TableView<Class_Reservation_Seance> table;
    @FXML
    private TableColumn<Class_Reservation_Seance, Integer> idColonne;
    @FXML
    private TableColumn<Class_Reservation_Seance, Integer> idseanceColonne;
    @FXML
    private TableColumn<Class_Reservation_Seance, Integer> nbPlaceColonne;
    @FXML
    private TableColumn<Class_Reservation_Seance, String> etatColonne;
    @FXML
    private JFXButton classbutton;
    @FXML
    private JFXButton reservbutton;
    @FXML
    private JFXButton seancebutton;
    @FXML
    private JFXButton frontgo;
    @FXML
    private Label numlabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Reservation_SeanceI cs = new Reservation_SeanceI();
            
            
      List<Class_Reservation_Seance> lc = cs.afficher_Reservation();
        
        ObservableList<Class_Reservation_Seance> data =
                  
        FXCollections.observableArrayList(lc );
          
        idColonne.setCellValueFactory(
                new PropertyValueFactory<>("id"));
//          idtab.setVisible(false);
        idseanceColonne.setCellValueFactory(
                new PropertyValueFactory<>("idseance"));
 
       
        nbPlaceColonne.setCellValueFactory(
                new PropertyValueFactory<>("nbplace"));
 
        
        etatColonne.setCellValueFactory(
                new PropertyValueFactory<>("etat"));
        
        
               
        table.setItems(data);
    }    

 

    @FXML
    private void gotoclasses(MouseEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("SalleCRUD.fxml"));
  
            Parent root = loader.load();
            reservbutton.getScene().setRoot(root);  
    }

    @FXML
    private void gotoreserv(MouseEvent event) {
    }

    @FXML
    private void gotoseances(MouseEvent event)throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("SeanceCRUD.fxml"));
  
            Parent root = loader.load();
            reservbutton.getScene().setRoot(root);  
    }

    @FXML
    private void gotofront(MouseEvent event)  throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/Clientinterfaces/AfficherSeanceClient.fxml"));
  
            Parent root = loader.load();
            reservbutton.getScene().setRoot(root);  
    }

}
