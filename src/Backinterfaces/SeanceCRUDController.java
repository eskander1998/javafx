/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backinterfaces;

import Model.Class_Seance;
import Service.SeanceI;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class SeanceCRUDController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField heure;
    @FXML
    private TextField idsalle;
    @FXML
    private TextField capacite;
    @FXML
    private Button btninscr;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private TableView<Class_Seance> table;
    @FXML
    private TableColumn<Class_Seance, String> nomtab;
    @FXML
    private TableColumn<Class_Seance, Integer> heuretab;
    @FXML
    private TableColumn<Class_Seance, String> coachtab;
    @FXML
    private TableColumn<Class_Seance, Integer> capacitetab;
    @FXML
    private TableColumn<Class_Seance, Date> datetab;
    @FXML
    private TableColumn<Class_Seance, String> imagetab;
    @FXML
    private TableColumn<Class_Seance, Integer> idsalletab;
    @FXML
    private TableColumn<Class_Seance, Integer> idtab;
    @FXML
    private TextField coach;
    @FXML
    private JFXButton reservbutton;
    @FXML
    private JFXButton frontgo;
    @FXML
    private Label nomlabel;
    @FXML
    private Label heurelabel;
    @FXML
    private Label coachlabel;
    @FXML
    private Label capacitelabel;
    @FXML
    private Label idsallelabel;
    @FXML
    private DatePicker DateEventField;
    @FXML
    private TextField ImageEventField;
    @FXML
    private Label datelabel;
    @FXML
    private Label imagelabel;
    @FXML
    private JFXButton classbutton;
    @FXML
    private JFXButton seancebutton;
    @FXML
    private TextField rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            SeanceI cs = new SeanceI();

        List<Class_Seance> lc = cs.afficherseance();
        
        ObservableList<Class_Seance> data =
                  
        FXCollections.observableArrayList(lc );
          
        idtab.setCellValueFactory(
                new PropertyValueFactory<>("id"));
//          idtab.setVisible(false);
        nomtab.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
       
        heuretab.setCellValueFactory(
                new PropertyValueFactory<>("heure"));
 
        
        coachtab.setCellValueFactory(
                new PropertyValueFactory<>("coach"));
        
        
        
        datetab.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        capacitetab.setCellValueFactory(
                new PropertyValueFactory<>("capacite"));
        imagetab.setCellValueFactory(
                new PropertyValueFactory<>("image"));
            idsalletab.setCellValueFactory(
                new PropertyValueFactory<>("idsalle"));
               
        table.setItems(data);
    }    
    @FXML
    private void modifierchamp(MouseEvent event) throws ParseException {
               if ((nom.getText().isEmpty())||(heure.getText().isEmpty())||(coach.getText().isEmpty())||(DateEventField.getValue() == null)||
                            (capacite.getText().isEmpty())||(ImageEventField.getText().isEmpty())||(idsalle.getText().isEmpty()))
       {

   if (nom.getText().isEmpty())
       nomlabel.setText("nom invalide");
   else
              nomlabel.setText("");

   if (heure.getText().isEmpty())
       heurelabel.setText("heure invalid");
   else
              heurelabel.setText("");

   if (coach.getText().isEmpty())
       coachlabel.setText("coach invalid");
   else
              coachlabel.setText("");

   if(DateEventField.getValue() == null)
       datelabel.setText("date invalid");
    else
              datelabel.setText("");

       if (capacite.getText().isEmpty())
           capacitelabel.setText("capacite vide");
          else
              capacitelabel.setText("");

         if (ImageEventField.getText().isEmpty())
           imagelabel.setText("image  vide");
            else
              imagelabel.setText("");
 if (idsalle.getText().isEmpty())
           idsallelabel.setText("idsalle  vide");
            else
              idsallelabel.setText("");
       }
       else
           
{
        
                 String  Datesea= String.valueOf(DateEventField.getValue());

                 Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(Datesea);

                 int b=Integer.parseInt(heure.getText());
                  int a=Integer.parseInt(capacite.getText());
                  int c=Integer.parseInt(idsalle.getText());
         int k=Integer.parseInt(idtab.getText());

     Class_Seance e=new Class_Seance(k,nom.getText(),b,coach.getText(),Date,a,0,ImageEventField.getText(),"en cours",c);
            SeanceI cs = new SeanceI();
cs.modifierseance(e);
List<Class_Seance> lc = cs.afficherseance();
        
        
          ObservableList<Class_Seance> data =
                  
        FXCollections.observableArrayList(lc);
        table.setItems(data);
        }
    }

    @FXML
    private void supprimer(MouseEvent event) {
            SeanceI cs = new SeanceI();
              System.out.println("aaasdsa");
         int b=Integer.parseInt(idtab.getText());
        System.out.println("yess"+b);
           cs.DeleteClasse(b);        
         List<Class_Seance> lc2 = cs.afficherseance();
        
          ObservableList<Class_Seance> data =
                  
        FXCollections.observableArrayList(lc2 );
          
      
 
 
 
        table.setItems(data);
    }

    @FXML
    private void afficherform(MouseEvent event) {
        Class_Seance t = table.getSelectionModel().getSelectedItem();         
          
nom.setText(String.valueOf(t.getNom()));
heure.setText(String.valueOf(t.getHeure()));
coach.setText(String.valueOf(t.getCoach()));
capacite.setText(String.valueOf(t.getCapacite()));
ImageEventField.setText(String.valueOf(t.getImage()));
idsalle.setText(String.valueOf(t.getIdsalle()));
DateEventField.setValue(Instant.ofEpochMilli(t.getDate().getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate());
idtab.setText(String.valueOf(t.getId()));
    }


    @FXML
    private void gotoreserv(MouseEvent event)throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReservationSeanceBack.fxml"));
  
            Parent root = loader.load();
            reservbutton.getScene().setRoot(root);  
    }

     @FXML
    private void gotofront(MouseEvent event)  throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/Clientinterfaces/AfficherSeanceClient.fxml"));
  
            Parent root = loader.load();
            reservbutton.getScene().setRoot(root);  
    }

    @FXML
    private void importerImage(MouseEvent event) {
        
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected !=null )
        {
            String extension = selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid picture");
        
        alert.setContentText("Invalid picture format (only jgp/png available)"); 
     
        alert.showAndWait();
        ImageEventField.setText("");
            }else
            ImageEventField.setText(selected.getName());
        }
    }

    @FXML
    private void gotoclasses(MouseEvent event) throws IOException {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("SalleCRUD.fxml"));
  
            Parent root = loader.load();
            reservbutton.getScene().setRoot(root);    
    }

    @FXML
    private void gotoseances(MouseEvent event) {
    }

    @FXML
    private void ajouterseance(ActionEvent event) throws ParseException {
           if ((nom.getText().isEmpty())||(heure.getText().isEmpty())||(coach.getText().isEmpty())||(DateEventField.getValue() == null)||
                            (capacite.getText().isEmpty())||(ImageEventField.getText().isEmpty())||(idsalle.getText().isEmpty()))
       {

   if (nom.getText().isEmpty())
       nomlabel.setText("nom invalide");
   else
              nomlabel.setText("");

   if (heure.getText().isEmpty())
       heurelabel.setText("heure invalid");
   else
              heurelabel.setText("");

   if (coach.getText().isEmpty())
       coachlabel.setText("coach invalid");
   else
              coachlabel.setText("");

   if(DateEventField.getValue() == null)
       datelabel.setText("date invalid");
    else
              datelabel.setText("");

        if (capacite.getText().isEmpty())
           capacitelabel.setText("capacite vide");
          else
              capacitelabel.setText("");

         if (ImageEventField.getText().isEmpty())
           imagelabel.setText("image  vide");
            else
              imagelabel.setText("");
 if (idsalle.getText().isEmpty())
           idsallelabel.setText("idsalle  vide");
            else
              idsallelabel.setText("");
       }
       else
           
{
        
                 String  Datesea= String.valueOf(DateEventField.getValue());

                 Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(Datesea);

                 int b=Integer.parseInt(heure.getText());
                  int a=Integer.parseInt(capacite.getText());
                  int c=Integer.parseInt(idsalle.getText());

     Class_Seance e=new Class_Seance(0,nom.getText(),b,coach.getText(),Date,a,0,ImageEventField.getText(),"en cours",c);
            SeanceI cs = new SeanceI();
cs.ajouterseance(e);
List<Class_Seance> lc = cs.afficherseance();
        
        
          ObservableList<Class_Seance> data =
                  
        FXCollections.observableArrayList(lc);
        table.setItems(data);
        }
    }

    @FXML
    private void rechtest(KeyEvent event) {
                    SeanceI cs = new SeanceI();

List<Class_Seance> lc = cs.afficherseance();
        
        
          ObservableList<Class_Seance> list =
                  
        FXCollections.observableArrayList(lc);
          
          
          FilteredList<Class_Seance> filtereddata= new FilteredList<>(list,b->true);
rech.textProperty().addListener((observable,oldValue,newValue)->{
        filtereddata.setPredicate(seance -> {
            
            if (newValue == null || newValue.isEmpty()){
                return true;
        }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (seance.getNom().toLowerCase().indexOf(lowerCaseFilter)!= -1)
            return true;
                else
                    if(seance.getEtat().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                                    return true;
else
                    if(seance.getCoach().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                                    return true;
                    else
                    if(String.valueOf(seance.getIdsalle()).indexOf(lowerCaseFilter)!= -1)
                                    return true;
                        else
                    if(String.valueOf(seance.getCapacite()).indexOf(lowerCaseFilter)!= -1)
                                    return true;
                        else   
                         if(String.valueOf(seance.getHeure()).indexOf(lowerCaseFilter)!= -1)
                                    return true;
                        else
                    if(seance.getImage().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                                    return true;
                        
                        else    
                              if(String.valueOf(seance.getDate()).indexOf(lowerCaseFilter)!= -1)
                                    return true;
                else
            return false;
                });
                
    });
SortedList<Class_Seance> sortedData = new SortedList<>(filtereddata);
sortedData.comparatorProperty().bind(table.comparatorProperty());
table.setItems(sortedData);
    }

    @FXML
    private void rechtest2(KeyEvent event) {
    }
    
}
