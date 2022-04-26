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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author prest
 */
public class AddCharFXMLController implements Initializable {

    // declare the references to the objects that are viable on the addChar window 
    @FXML
    private MenuButton charDropDown;
    @FXML
    private ToggleGroup kartOptions;
    @FXML
    private DialogPane newCompetitorDialogue;
    @FXML
    private TextField firstName;
    @FXML
    private TextField id;
    @FXML
    private TextField lastName;
    @FXML
    private ToggleGroup characters;
    @FXML
    private Label addCharErrorReporter;
    @FXML
    private RadioButton standardKart;
    @FXML
    private RadioButton pipeFrame;
    @FXML
    private RadioButton mach8;
    @FXML
    private RadioButton steelDriver;
    @FXML
    private RadioButton catCruiser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // when the OK button is pressed...
    // gather all information that was given and process the creation of a new competitor
    // or return the appropriote error feedback to the user
    @FXML
    private void btnOK(ActionEvent event) {

        // make if else else else block to check if all data fields have been entered, and return error to error log if true 
        // Gather all information entered into the window
        String fName = firstName.getText();
        String lName = lastName.getText();
        int charID = Integer.valueOf(id.getText());
        String character = ((RadioMenuItem) (characters.getSelectedToggle())).getText();
        String vehicle = ((RadioButton) (kartOptions.getSelectedToggle())).getText();

        // these are the three types of errors that are handled
        // when no first name is given
        if (!(fName.length() > 0)) {
            addCharErrorReporter.setText("Error: You must use a legal first name");
        }
        //when no last name is given
        else if (!(lName.length() > 0)) {
            addCharErrorReporter.setText("Error: You must use a legal last name");
        }
        // if an illegal id is given
        else if (String.valueOf(charID).length() < 7 || String.valueOf(charID).length() > 7) {
            addCharErrorReporter.setText("Error: You must use a legal character ID number");
        }

        // in the case that none of the errors were met, finally make the character
        else {

            // create the char and plug in found values to the contructor
            Competitor ch = new Competitor(charID, fName, lName, character, vehicle);
            // save that newly constructed 
            FXMain.getChars().add(ch);
            System.out.println(ch.toString()); // tool for confirming inputted character on the backend
            // finally close this menu once tasks are successfully carried out
            FXMain.getAddCharStage().close();
        }

    }

    //HELPER METHOD()
    // Name: verifyID()
    // Input: String id
    // Output: boolean
    // purpose: used to determine whether a given ID meets the qualifications
    //          of what it means to be a legal id 
    //          - True when (A) contains only digits and (B) its length is equal to 7 
    public static boolean verifyID(String id) {

        // if it is a 7 digit integer, return true
        if (containsOnlyDigits(id) && id.length() == 7) {
            return true;
        }

        else {
            return false;
        }

    }

    //HELPER METHOD()
    // Name: containsOnlyDigits()
    // Input: String s
    // Output: boolean
    // purpose: used within the verify id to check whether a given string is composed of only digits
    public static boolean containsOnlyDigits(String s) {

        // loop through the string and check if each character is a digit 
        for (int i = 0; i < s.length(); i++) {

            // the character of this iteration
            char num = s.charAt(i);

            // if something that is not a digit is found, then method returns false 
            if (!Character.isDigit(num)) {
                return false;
            }

        }
        // if the for loop reaches its natural conlclusion, we can safely assume that it only contains digits
        return true;
    }

}
