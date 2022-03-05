/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Model.Class_Seance;
import Service.SeanceI;
import Util.MaConnexion;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherSeanceClientController implements Initializable {
 public static int place_reste= 0;

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private ComboBox combobox_recherType;
    private VBox SeanceChoisieCarte;
    @FXML
    private Label idEvent;
    @FXML
    private Label idOrganisateurLabel;
    @FXML
    private Label imgSeanceLabel;
    @FXML
    private Label TitreSeanceCarte;
    @FXML
    private Label LieuSeanceCarte;
    private ImageView seanceImg;
    @FXML
    private Label DateSeanceCarte;
    @FXML
    private Label PlaceRestanteCarte;
    @FXML
    private Label SeanceEffectueCarte;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton btngestionreservation;
    @FXML
    private JFXButton btngoback;
    @FXML
    private JFXButton btngestionSeances;

     private Image image;
     private MyListener myListener;
    
     private Statement ste;
     MaConnexion db=new MaConnexion();
     private Connection con=db.getCnx();
     
     SeanceI cs = new SeanceI();

    List<Class_Seance> seancess = cs.afficher_Seance();
    @FXML
    private Label idreserv;
    /**
     * Initializes the controller class.
     */
    
    private List<String> datec = new ArrayList<>();
    
          
    private List<Class_Seance> getData() {
    List<Class_Seance> seances = cs.afficher_Seance();
    
          ObservableList<Class_Seance> data =
                  
        FXCollections.observableArrayList(seances );

     
        return data;
    }
    
    
    private void setChosenSeance(Class_Seance e) {
        String id =String.valueOf(e.getId());
//        idOrganisateurLabel.setText(idOrga);
        idEvent.setText(id);
        TitreSeanceCarte.setText(e.getNom());
        LieuSeanceCarte.setText(String.valueOf(e.getIdsalle()));
        
        Date d=e.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");  
        String strDate = dateFormat.format(d); 
        DateSeanceCarte.setText(strDate);
        imgSeanceLabel.setText(e.getImage());
       place_reste= e.getCapacite()-e.getNbreservation();
       if(place_reste<=0)
       { PlaceRestanteCarte.setText("Complet");}
       
       else {PlaceRestanteCarte.setText(String.valueOf(place_reste));}
       
       if(e.getEtat()=="effectue")
       {
           SeanceEffectueCarte.setText("Cette Séance a déja eu lieu");
       }
       else {
           
        SeanceEffectueCarte.setText(null);
       }
    
        
        String imgg= e.getImage();
        String ch="/imgSeance/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));

    }
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
       // evenementss.addAll(getData());
       

              SeanceI cs = new SeanceI();
                
              datec=cs.calculdate();

              
                         ObservableList<String> list = FXCollections.observableArrayList();
      for (int i = 0; i < datec.size(); i++) {
                        list.add(datec.get(i));

                    }
      
                                combobox_recherType.setItems(list);
                                
        
        if (seancess.size() > 0) {
            setChosenSeance(seancess.get(0));
            myListener = new MyListener() {
                public void onClickListener(Class_Seance e) {
                    setChosenSeance(e);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < seancess.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemSeance.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemClassSeanceController ItemClassSeanceController = fxmlLoader.getController();
                ItemClassSeanceController.setData(seancess.get(i),myListener);

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
    private void rechercherType(ActionEvent event) {
        
           grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
                  String Typee= combobox_recherType.getSelectionModel().getSelectedItem().toString();
                  
             if (seancess.size() > 0) {
            setChosenSeance(seancess.get(0));
            myListener = new MyListener() {
                public void onClickListener(Class_Seance e) {
                    setChosenSeance(e);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < seancess.size(); i++) {
                if(Typee.equals(seancess.get(i).getDate().toString()))
                                {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemSeance.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemClassSeanceController ItemClassSeanceController = fxmlLoader.getController();
                ItemClassSeanceController.setData(seancess.get(i),myListener);

                if (column == 3) {
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
            }}
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
    private void gotoback(MouseEvent event)throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/Backinterfaces/SalleCRUD.fxml"));
  
            Parent root = loader.load();
            btngoback.getScene().setRoot(root);   
    }

    @FXML
    private void gotogestionseance(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSeancesClient.fxml"));
  
            Parent root = loader.load();
            btngestionSeances.getScene().setRoot(root);   
    }
}
