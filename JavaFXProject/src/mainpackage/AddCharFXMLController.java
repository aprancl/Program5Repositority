/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuButton;
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
    private TextField name;
    @FXML
    private TextField id;

    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnOK(ActionEvent event) {
        
        // assign 
        String charName = name.getText();
        int charID = Integer.valueOf(id.getText());
        String kart = "dummy kart"; //kartOptions.getSelectedToggle();
        
        
    }
    
}
