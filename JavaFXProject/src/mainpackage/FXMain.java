// Name: Anthony R. Prancl && Preston N. Reed
// Date: 4/26/2022
// Email: aprancl@mocs.flsouthern.edu && preed@mocs.flsouthern.edu
// Course Number: CSC 2290 - 002
// Assignment Title: Program 5: Java FX Group Project
// Florida Southern College Honor Code: "I will practice academic and personal 
//                                       integrity and excellence of character 
//                                       and expect the same from others."

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
 * @author anthonyprancl
 * @author2 Nick Reed
 */

// Main CLASS that is starting the entire program
public class FXMain extends Application {
    
    
    // These are the supporting stages that will be opened in tandem with the main window
    //*important* the stages must be initialized to null so as to avoid NullPointerException
    private static Stage addCharStage = null;
    private static Stage searchCharStage = null;
    private static Stage statsStage = null;

    // the container holding all of our characters and the record times
    private static ArrayList<Competitor> chars = new ArrayList<>();
    private static int[] recordTimes = {115, 117, 115, 100};
    // ^ used to calculate new times for competitors

    
    // this is the start method, which will complete the instructions in its code
    // block on opening the main window (or rather, right before to be more specific)
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Create and display the main window
        
        // this <root> is pointing to the information of the fxml that we want to display
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
           

        // set scene as the main window
        Scene scene = new Scene(root);

        // set basic info + show 
        primaryStage.setTitle("Mario Kart Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Call all stage creating methods in case of use.
        createAddCharStage();
        createSearchCharStage();
        createStatsStage();

    }

    // Instantiates the addChar (new competitor creator) stage.
    public void createAddCharStage() {
        
        // initialize the previosly null stage
        addCharStage = new Stage();
        // basic setup
        addCharStage.setTitle("Add New Competitor");
        addCharStage.setAlwaysOnTop(true);
        addCharStage.setResizable(false);
        // this stops the main window from being tampered with when the user
        // is on an agacent window
        addCharStage.initModality(Modality.APPLICATION_MODAL);
    }

    // Instantiates the searchChar (competitor search dialogue) stage.
    public void createSearchCharStage() {
        // initialize the previosly null stage
        searchCharStage = new Stage();
        // basic setup
        searchCharStage.setTitle("Competitor Search Service");
        searchCharStage.setAlwaysOnTop(true);
        searchCharStage.setResizable(false);
        // this stops the main window from being tampered with when the user
        // is on an agacent window
        searchCharStage.initModality(Modality.APPLICATION_MODAL);
    }
    
    // Instantiates the Statistics (informational) stage.
    public void createStatsStage(){
        // initialize the previosly null stage
        statsStage = new Stage();
        // basic setup
        statsStage.setTitle("Statistics");
        statsStage.setAlwaysOnTop(true);
        statsStage.setResizable(false);
        // this stops the main window from being tampered with when the user
        // is on an agacent window
        statsStage.initModality(Modality.APPLICATION_MODAL);
    }

    // Getters for Stages accessible from the main menu.
    public static Stage getAddCharStage() {
        return addCharStage;
    }

    public static Stage getSearchCharStage() {
        return searchCharStage;
    }
    
    public static Stage getStatsStage(){
        return statsStage;
    }
    
    public static int []getBestTimes(){
        return recordTimes;
    }

    // Getter for Arraylist of competitors, that we are still commited to naming 'chars'.
    public static ArrayList<Competitor> getChars() {
        return chars;
    }

    /**
     * @param args the command line arguments
     */
    
    // finally, the main method, which just launches this program
    public static void main(String[] args) {

        launch(args);

    }

}
