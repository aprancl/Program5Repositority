/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author prest
 */
public class StatsFXMLController implements Initializable {

    @FXML
    private Label StatsErrorBar;
    @FXML
    private Label SAStats;
    @FXML
    private Label SALeader;
    @FXML
    private Label DSStats;
    @FXML
    private Label DSLeader;
    @FXML
    private Label EDStats;
    @FXML
    private Label EDLeader;
    @FXML
    private Label MWStats;
    @FXML
    private Label MWLeader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static void displayStatistics(Competitor[] competitors) {

        if (Competitor.getNumCompetitors() == 0) {
            System.out.println("There are no statistics recorded, as there are no\n"
                    + "competitors currently registered in the system.\n");
        }

        else {
            // used to store average seconds for each track for latter
            String[] box = new String[4];

            for (int i = 0; i < 4; i++) {

                int sumSeconds = 0;
                int j;
                int skipCounter = 0;
                for (j = 0; j < Competitor.getNumCompetitors(); j++) {

                    //makes those who have not performed time trial not be thrown into the calculations 
                    if (competitors[j].getBestTimes(i) == 0) {
                        // *important* this counter specifically will be used 
                        // to adjust the calculations to account for
                        // individuals that are overlooked
                        skipCounter++;
                        continue;
                    }
                    int seconds = competitors[j].getBestTimes(i);
                    sumSeconds += seconds;

                }
                // if there exists at least 1 competitor and they have not been skipped...
                if (j > 0 && skipCounter < j) {
                    //calculate and average
                    int avgSeconds = sumSeconds / (j - skipCounter);

                    // then add the averaged information to the "box"
                    box[i] = formatTime(avgSeconds);
                }
            }

            //Output Data     
            String tempSA = (box[0] == null) ? "no time trials recorded" : box[0];
            String tempDS = (box[1] == null) ? "no time trials recorded" : box[1];
            String tempED = (box[2] == null) ? "no time trials recorded" : box[2];
            String tempMW = (box[3] == null) ? "no time trials recorded" : box[3];

            System.out.println("Statistics of Completed Time Trials - Average Time per Track");
            System.out.printf("      Sunshine Airport: %s\n", tempSA);
            System.out.printf("      Dolphin Shoals:   %s\n", tempDS);
            System.out.printf("      Electrodrome:     %s\n", tempED);
            System.out.printf("      Mount Wario:      %s\n", tempMW);
            System.out.println();
        }

    }

    public static String formatTime(int seconds) {

        // calculate minutes and seconds
        int minutes = seconds / 60; // get minutes 
        seconds = seconds % 60; // keep track of remainder seocnds 
        String formattedTime;

        // when and when not to place a zero in front of the seconds 
        formattedTime = (seconds < 10) ? "" + minutes + "'0" + seconds + "\"" : "" + minutes + "'" + seconds + "\"";

        return formattedTime;
    }

}
