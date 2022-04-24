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

/**
 * FXML Controller class
 *
 * @author anthonyprancl
 */
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class MainFXMLController implements Initializable {

    // this is the variable that is pointing to the central list of characters on the main window
    @FXML
    private static ListView charactersInSystem;

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
    private Button displayStatsButton;
    @FXML
    private ToggleGroup timeTrialToggle;
    private static Random rng = new Random();

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

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainpackage/AddCharFXML.fxml"));
            Parent root = loader.load(); // error

            FXMain.getAddCharStage().setScene(new Scene(root));

            FXMain.getAddCharStage().show();

        } catch (IOException ex) {
            System.out.println(ex);   // .getCause()        
        }
    }

    public void openSearchStage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainpackage/SearchCharFXML.fxml"));
            Parent root = loader.load();

            FXMain.getSearchCharStage().setScene(new Scene(root));

            FXMain.getSearchCharStage().show();

        } catch (IOException ex) {
            System.out.println(ex);   // .getCause()        
        }
    }

    @FXML
    private void addCharacter(ActionEvent event) {
        openCharStage();

        // update the listview window
        FXMain.getChars().forEach((competitor) -> {
            charactersInSystem.getItems().add(competitor.toString()); // important
        });

        // update the char stage
    }

    //  ---Beginning of Search System methods.
    // Will be for checking name input for legal characters only. Will follow similar path to the searchIDField. Not done with search by name and I regret saying this would be the easy part.
    public boolean containsOnlyNameChars(String s) {
        char c;

        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (!(Character.isLetter(c) || c == ' ' || c == '-')) {     // --THIS METHOD IS NOT IMPLEMENTED YET--
                return false;
            }
        }
        return true;
    }

    // Method that reads String input from searchIDField and parses it into an integer id if it is 7 chracters in length and contains only digits.
    // This ultimately calls the searchIDAndDisplayChar() if id is valid.
    @FXML
    public void readAndSearchID() {
        int id;
        String s = (searchIDField.getText());
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
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Method that searches the competitor arrayList for a match by ID. Opens searchCharFXML.
    public void searchIDAndDisplayChar(int id) {
        ArrayList<Competitor> chars = FXMain.getChars();
        for (Competitor comp : chars) {
            if (comp.getId() == id) {
                charToDisplay = comp;
                openSearchStage();
            }
        }
    }

    public static int getSearchCharID() {
        return searchCharID;
    }

    public static Competitor getCharToDisplay() {
        return charToDisplay;
    }

    // this method, when a search by name is initialized by user, will search for the given user, and return their info on the proper window
    @FXML
    private void ReadAndSearchName(ActionEvent event) {

        String queryFullName = searchNameField.getText();

        // loop through our competitors ArrayList
        for (int i = 0; i < Competitor.getNumCompetitors(); i++) {
            String compFirstName = FXMain.getChars().get(i).getFirstName();
            String compLastName = FXMain.getChars().get(i).getLastName();
            String compFullName = compFirstName + " " + compLastName;

            if (compFullName.equalsIgnoreCase(queryFullName)) {
                charToDisplay = FXMain.getChars().get(i);
                openSearchStage();
                return;

            }

        }
        // report to error log
        errorReporter.setText(String.format("Competitor \"%s\" was not found in the system.\n\n", queryFullName));

    }

    public static ListView getCharactersInSystem() {
        return MainFXMLController.charactersInSystem;
    }

    @FXML
    private void performTimeTrial(ActionEvent event) {

        if (Competitor.getNumCompetitors() < 1) {
            errorReporter.setText("There are not competitors entered in the system");
        }

        else {
            // have user select a track 
            String track = ((ToggleButton) (timeTrialToggle.getSelectedToggle())).getText();

            // loop through competitors and generate their times for the particular given track 
            int newTime;
            for (int i = 0; i < Competitor.getNumCompetitors(); i++) {

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

            
            errorReporter.setText("All competitors have new times recorded");

        }

    }

}
