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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author prest
 */
public class AddCharFXMLController implements Initializable {

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

    @FXML
    private void btnOK(ActionEvent event) {

        // get and assign the values of variables to a new compettier obejct 
        String fName = firstName.getText();
        String lname = lastName.getText();
        int charID = Integer.valueOf(id.getText());
        String character = "Luigi"; //charDropDown.getText(); // <-- is it really that easy? is that actually going to work?
        String vehicle = ((RadioButton) (kartOptions.getSelectedToggle())).getText();// not sure how to loop through 

        // somehow, this block isnt cathing the conditions
        if (!(fName.length() > 0)) {
            addCharErrorReporter.setText("Error: You must use a legal first name");
        }
        else if (!(fName.length() > 0)) {
            addCharErrorReporter.setText("Error: You must use a legal last name");
        }
        else if (!(String.valueOf(charID).length() > 0)) {
            addCharErrorReporter.setText("Error: You must use a legal character ID number");
        }
        // also for character and vehicle

        // make if else else else block to check if all data fields have been entered, and return error to error log if true
        else {
            Competitor ch = new Competitor(charID, fName, lname, character, vehicle);

            // somehow add to the arraylist using chars.add(ch);
            FXMain.getChars().add(ch);

            System.out.println(ch.toString());
            // issue, right now, the list view points to null, which is a problem
            // okay so this no longer points to null but its wont print 
            MainFXMLController.getChractersInSystem().getItems().add(ch.toString());
            //closes this stage
            FXMain.getAddCharStage().close();
        }

    }

    public static boolean verifyID(String id) {

        if (containsOnlyDigits(id) && id.length() == 7) {
            return true;
        }

        else {
            return false;
        }

    }

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
