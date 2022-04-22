/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author anthonyprancl
 */
public class MainFXMLController implements Initializable {

    // this is the variable that is pointing to the central list of characters on the main window
    @FXML
    private ListView<?> chractersInSystem;

    // not sure what this is, but I shall just leave it
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void openCharStage() {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoryFXML.fxml")); // /Users/anthonyprancl/Documents/Classes/NetBeansProjects/JavaFXLearning/CalculatorJavaFX/src/MainPackage/HistoryFXML.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainpackage/AddCharFXML.fxml"));
            Parent root = loader.load(); // error

            //root = FXMLLoader.load(getClass().getResource("HistoryFXML.fxml"));
            FXMain.getAddCharStage().setScene(new Scene(root));

//            HistoryFXMLController historyFXMLController = loader.getController();
//            historyFXMLController.initializeCalculation(calculation_history);
//            
            FXMain.getAddCharStage().show();

        } catch (IOException ex) {
            System.out.println(ex);   // .getCause()        
        }

    }

    @FXML
    private void addCharacter(ActionEvent event) {
        openCharStage();
    }

}
