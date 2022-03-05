/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backinterfaces;

import Model.Class_salle;
import Service.SalleI;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class SalleCRUDController implements Initializable {

    @FXML
    private TextField num;
    @FXML
    private Button btninscr;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private TableView<Class_salle> table;
    @FXML
    private TableColumn<Class_salle, Integer> idtab;
    @FXML
    private TableColumn<Class_salle, Integer> numtab;
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
            SalleI cs = new SalleI();

         List<Class_salle> lc = cs.affichersalle();
        
          ObservableList<Class_salle> data =
                  
        FXCollections.observableArrayList(lc );
          
        idtab.setCellValueFactory(
                new PropertyValueFactory<>("id_salle"));
        numtab.setCellValueFactory(
                new PropertyValueFactory<>("numero_salle"));
 
   
        table.setItems(data);
        // TODO
    }    

    @FXML
    private void ajoutersalle(ActionEvent event) {
        if ((num.getText().isEmpty()))
       {

   if (num.getText().isEmpty())
       numlabel.setText("numero invalide");
   else
              numlabel.setText("");
    }
         else
           
{
    Class_salle s= new Class_salle(0,num.getText());
                SalleI cs = new SalleI();
cs.ajoutersalle(s);
  List<Class_salle> lc = cs.affichersalle();
        
          ObservableList<Class_salle> data =
                  
        FXCollections.observableArrayList(lc );
            table.setItems(data);

}
    }

    @FXML
    private void modifierchamp(MouseEvent event) {
          if ((num.getText().isEmpty()))
       {

   if (num.getText().isEmpty())
       numlabel.setText("numero invalide");
   else
              numlabel.setText("");
    }
         else
           
{
             int b=Integer.parseInt(idtab.getText());
    Class_salle s= new Class_salle(b,num.getText());
                SalleI cs = new SalleI();
cs.modifiersalle(s);
  List<Class_salle> lc = cs.affichersalle();
        
          ObservableList<Class_salle> data =
                  
        FXCollections.observableArrayList(lc );
            table.setItems(data);

}
        
    }

    @FXML
    private void supprimer(MouseEvent event) {
                SalleI cs = new SalleI();
              
         int b=Integer.parseInt(idtab.getText());
                  System.out.println("dasss"+ b);

           cs.DeleteClasse(b);        
        List<Class_salle> lc = cs.affichersalle();
        
          ObservableList<Class_salle> data =
                  
        FXCollections.observableArrayList(lc );
            table.setItems(data);
        
    }

    @FXML
    private void afficherform(MouseEvent event) {
         Class_salle t = table.getSelectionModel().getSelectedItem();         
          
idtab.setText(String.valueOf(t.getId_salle()));
num.setText(String.valueOf(t.getNumero_salle()));
    }

    @FXML
    private void gotoclasses(MouseEvent event) {
    }

    @FXML
    private void gotoreserv(MouseEvent event)throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReservationSeanceBack.fxml"));
  
            Parent root = loader.load();
            reservbutton.getScene().setRoot(root);  
    }

    @FXML
    private void gotoseances(MouseEvent event) throws IOException {
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
