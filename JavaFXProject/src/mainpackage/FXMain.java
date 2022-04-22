/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package mainpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author anthonyprancl
 * @author2 Nick Reed
 */
public class FXMain extends Application {

    private static Stage addCharStage = null;
    // this is the start of the main
    // this is another practice commit 
    @Override
    public void start(Stage primaryStage) {

        // Create and display the main window
        
        Parent root = null;
        try {
            // refer to the FXML file specific to the "MainFXMLController"
            root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        // set scene as the main window
        Scene scene = new Scene(root);

        // set basic info + show 
        primaryStage.setTitle("Mario Kart Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();

        createAddCharStage();
    }
    
    
    
    public void createAddCharStage() {
        addCharStage = new Stage();
        addCharStage.setTitle("Add Your Character");
        addCharStage.setAlwaysOnTop(true);
        addCharStage.setResizable(false);
    }
    
    public static Stage getAddCharStage(){
        return addCharStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // the container holding all of our characters 
        ArrayList<Competitor> chars = new ArrayList<>();
        
        
        launch(args);
        
    }

}
