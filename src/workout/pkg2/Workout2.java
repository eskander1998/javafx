/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workout.pkg2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Farah BEN HADID
 */
public class Workout2 extends Application {
    
    @Override
    public void start(Stage stage){
        try{Parent root = FXMLLoader.load(getClass().getResource("/Clientinterfaces/AfficherSeanceClient.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        }
        catch(IOException ex){
        ex.printStackTrace();}
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
