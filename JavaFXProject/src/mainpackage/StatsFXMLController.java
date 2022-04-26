/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpackage;

import java.net.URL;
import java.util.ArrayList;
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<Competitor> chars = FXMain.getChars();

        // for average Times
        SAStats.setText(displayStatistics(0, chars));
        DSStats.setText(displayStatistics(1, chars));
        EDStats.setText(displayStatistics(2, chars));
        MWStats.setText(displayStatistics(3, chars));

        // for Leasder and best time
        SALeader.setText(findLeader(0, chars));
        DSLeader.setText(findLeader(1, chars));
        EDLeader.setText(findLeader(2, chars));
        MWLeader.setText(findLeader(3, chars));

    }

    public static String findLeader(int track, ArrayList<Competitor> competitors) {
        String data = "";

        // loop through track to find best time 
        int bestTime = Integer.MAX_VALUE;
        int bestCharIndex = -1;
        for (int i = 0; i < competitors.size(); i++) {

            int time = competitors.get(i).getBestTimes(track);

            if (time < bestTime && time > 0) {
                bestTime = time;
                bestCharIndex = i;
            }

        }

        if (bestCharIndex == -1) {
            return "No times recorded";

        }
        else {

            data += String.format("Name: %s\n", competitors.get(bestCharIndex).getFullName());
            data += String.format("Time: %s", formatTime(bestTime));

        }

        return data;
    }

    public static String displayStatistics(int track, ArrayList<Competitor> competitors) {
        String data = "";

        int avgSeconds = -1;
        int sumSeconds = 0;
        int j;
        int skipCounter = 0;

        for (j = 0; j < Competitor.getNumCompetitors(); j++) {

            //makes those who have not performed time trial not be thrown into the calculations 
            if (competitors.get(j).getBestTimes(track) == 0) {
                // *important* this counter specifically will be used 
                // to adjust the calculations to account for
                // individuals that are overlooked
                skipCounter++;
                continue;
            }
            int seconds = competitors.get(j).getBestTimes(track);
            sumSeconds += seconds;

        }
        // if there exists at least 1 competitor and they have not been skipped...
        if (j > 0 && skipCounter < j) {
            //calculate and average
            avgSeconds = sumSeconds / (j - skipCounter);

        }

        String trackName = null;
        switch (track) {
            case 0:
                trackName = "Sunshine Airport";
                break;
            case 1:
                trackName = "Dolphin Shoals";
                break;
            case 2:
                trackName = "Electrodome";
                break;
            case 3:
                trackName = "Mount Wario";
                break;

        }

        //Output Data 
        if (avgSeconds == -1) {
            data += "no time trials recorded";
        }
        else {
            data += String.format("Average time for %s: %s", trackName, formatTime(avgSeconds));
        }

        return data;
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
