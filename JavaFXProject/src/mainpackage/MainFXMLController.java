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
import java.util.Random;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author anthonyprancl
 */
public class MainFXMLController implements Initializable {

    // Declare references that are pointing to objects in the .fxml file
    @FXML
    private ListView<String> charactersInSystem;

    private static int searchCharID;

    private static Competitor charToDisplay;
    @FXML
    private Button searchIDGoButton;
    @FXML
    private Button searchNameGoButton;
    @FXML
    private TextField searchIDField;
    @FXML
    private TextField searchNameField;
    @FXML
    private Label errorReporter;
    @FXML
    private ToggleGroup timeTrialToggle;
    private static Random rng = new Random();

    /**
     * Initializes the controller class.
     */
    // execule the instructions in this code block when the window is intiiailize
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // the only thing to accomplish is to set up the header for the all characters in system listview
        charactersInSystem.getItems().add("            Name                 |                 ID                |                                                Times                                             |");

    }

    // when the add character button is called, we want to execute the instructions 
    // necessary in making the new window
    public void openCharStage() {
        try {

            // namely...
            // creating a reference to the data within the fxml 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainpackage/AddCharFXML.fxml"));
            Parent root = loader.load();

            // setting the scene with the initialized root
            FXMain.getAddCharStage().setScene(new Scene(root));
            // finally, just show the stage
            FXMain.getAddCharStage().show();

            // must use the catch clause following the try, because an IOException can occur
            // AND becasue it is a checked exception, it must be handled appropriotly
        } catch (IOException ex) {
            // in reality, this is just here so it doesn't crash. 
            System.out.println(ex);
        }
    }

    // when the GO button is called for search for a chracter, we want to execute the instructions 
    // necessary in making the new window
    public void openSearchStage() {
        try {

            // namely...
            // creating a reference to the data within the fxml 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainpackage/SearchCharFXML.fxml"));
            Parent root = loader.load();

            // setting the scene with the initialized root
            FXMain.getSearchCharStage().setScene(new Scene(root));

            // finally, just show the stage
            FXMain.getSearchCharStage().show();

            // must use the catch clause following the try, because an IOException can occur
            // AND becasue it is a checked exception, it must be handled appropriotly
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // update the listview window
    // update the char stage
    @FXML
    private void addCharacter(ActionEvent event) {
        openCharStage();

    }

    // Method that reads String input from searchIDField and parses it into an 
    //integer id if it is 7 chracters in length and contains only digits.
    // This ultimately calls the searchIDAndDisplayChar() if id is valid.
    @FXML
    public void readAndSearchID() {

        // declare an id, are check if its string potential counterpart is legal
        int id;
        String s = (searchIDField.getText());
        // intitializes the id if and only if its length is equal to seven and it 
        //consists of strictly contains integers 
        if (s.length() == 7 && containsOnlyDigits(s) == true) {
            id = Integer.parseInt(s);
            searchIDAndDisplayChar(id);
        }
        else {
            // report to error log
            errorReporter.setText("Error: Invalid id entry. Please enter an id that is 7 integers long .");
        }
    }

    // Method to check if a string only contains digits. Stolen from my program3 file for reuse.
    public static boolean containsOnlyDigits(String s) {
        // loop through a given string, and determine whether the characters are digits or not 
        for (int i = 0; i < s.length(); i++) {
            // if at least one is found, return false to the call back
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        // other wise, the method return true
        return true;
    }

    // Method that searches the competitor arrayList for a match by ID. Opens searchCharFXML.
    public void searchIDAndDisplayChar(int id) {
        // get the competitors in the main file
        ArrayList<Competitor> chars = FXMain.getChars();

        // loop through the characters with a "for each" loop
        for (Competitor comp : chars) {
            //returns the first competitor to match the given id 
            if (comp.getId() == id) {
                charToDisplay = comp;
                // and then displays them
                openSearchStage();
            }
        }
    }

    // two getters for ease of use 
    public static int getSearchCharID() {
        return searchCharID;
    }

    public static Competitor getCharToDisplay() {
        return charToDisplay;
    }

    // this method, when a search by name is initialized by user, will search 
    // for the given user, and return their info on the proper window
    @FXML
    private void ReadAndSearchName(ActionEvent event) {

        // get the querries name by searching the textfield
        String queryFullName = searchNameField.getText();

        // loop through our competitors ArrayList
        for (int i = 0; i < Competitor.getNumCompetitors(); i++) {
            String compFirstName = FXMain.getChars().get(i).getFirstName();
            String compLastName = FXMain.getChars().get(i).getLastName();
            String compFullName = compFirstName + " " + compLastName;

            // if the nth competitor's full name matches our querry name, we return their reference
            // for displaying purposes
            if (compFullName.equalsIgnoreCase(queryFullName)) {
                charToDisplay = FXMain.getChars().get(i);
                openSearchStage();
                return;

            }

        }
        // report to error log
        errorReporter.setText(String.format("Competitor \"%s\" was not found in the system.\n\n", queryFullName));

    }

    public ListView getCharactersInSystem() {
        return this.charactersInSystem;
    }

    @FXML
    private void performTimeTrial(ActionEvent event) {

        // update the error log if there are no competitors in the system yet
        if (Competitor.getNumCompetitors() < 1) {
            errorReporter.setText("There are no competitors entered in the system");
        }

        // in the case that there are competitors entered into the system...
        else {
            // Get the user selected track
            String track = ((ToggleButton) (timeTrialToggle.getSelectedToggle())).getText();

            // loop through competitors and generate their times for the particular given track 
            int newTime;
            for (int i = 0; i < Competitor.getNumCompetitors(); i++) {

                // switch case depending on track
                switch (track) {
                    case "Sunshine Airport":
                        newTime = FXMain.getBestTimes()[0] + rng.nextInt(30);
                        FXMain.getChars().get(i).setBestTimes(0, newTime);
                        break;
                    case "Dolphin Shoals":
                        newTime = FXMain.getBestTimes()[1] + rng.nextInt(30);
                        FXMain.getChars().get(i).setBestTimes(1, newTime);
                        break;
                    case "Electrodome":
                        newTime = FXMain.getBestTimes()[2] + rng.nextInt(30);
                        FXMain.getChars().get(i).setBestTimes(2, newTime);
                        break;
                    case "Mount Wario":
                        newTime = FXMain.getBestTimes()[3] + rng.nextInt(30);
                        FXMain.getChars().get(i).setBestTimes(3, newTime);
                        break;
                }

            }

            errorReporter.setText(String.format("All competitors have new times recorded for: %s", track));

        }

    }

    @FXML
    private void openStatsWindow(ActionEvent event) {

        try {

            // namely...
            // creating a reference to the data within the fxml 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainpackage/StatsFXML.fxml"));
            Parent root = loader.load(); // error

            // setting the scene with the initialized root
            FXMain.getStatsStage().setScene(new Scene(root));

            // finally, just show the stage
            FXMain.getStatsStage().show();

        } catch (IOException ex) {
            // must use the catch clause following the try, because an IOException can occur
            // AND becasue it is a checked exception, it must be handled appropriotly
            System.out.println(ex);   // .getCause()        
        }
    }

    @FXML
    private void updateCharList(ActionEvent event) {

        // access the competitors entered into the system
        ArrayList<Competitor> chars = FXMain.getChars();
        
        // cleat the entire listview panel 
        charactersInSystem.getItems().clear();

        // redraw it with the updated information
        charactersInSystem.getItems().add("            Name                 |                 ID                |                                                Times                                             |");
        for (Competitor ch : chars) {// loops through each char are prints their unique tostring
            charactersInSystem.getItems().add(ch.toListViewString());
        }

    }

}
