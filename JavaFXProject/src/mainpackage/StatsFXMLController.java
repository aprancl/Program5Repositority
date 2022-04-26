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

    // FXML data members representing the FXIDs of the necessary fields.
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
    // Find and print the Name and Time of the fastest competitor on a given track 
    // given the track index and the ArrayList of Competitors.
    public static String findLeader(int track, ArrayList<Competitor> competitors) {
        String data = "";

        // loop through competitors by individual track to find best time 
        int bestTime = Integer.MAX_VALUE;
        int bestCharIndex = -1;
        for (int i = 0; i < competitors.size(); i++) {
            // Set variable time to the value of the time in the track index of the current competitor's 
            // bestTimes array.
            int time = competitors.get(i).getBestTimes(track);
            // If this time is bette than the current best time, then the bestTime variable is set as the current competitor's 
            // time for the track.
            if (time < bestTime && time > 0) {
                bestTime = time;
                bestCharIndex = i;
            }
        }
        // If the bestCharIndex never updated from -1, then no valid time was found during the loop
        // This means no time was recorded for any competitor, so the string is updated with this info.
        if (bestCharIndex == -1) {
            return "No times recorded";
        }
        else {
            // Otherwise, the string is formatted to return the following:
            // Name: {Competitor's Name}
            // Time: {Competitor's time for the track}
            data += String.format("Name: %s\n", competitors.get(bestCharIndex).getFullName());
            data += String.format("Time: %s", formatTime(bestTime));

        }

        return data;
    }

    // Method for calculating and returning a string representing the statistics of a track given the track
    //index(1-4 inclusive) and the ArrayList of competitors.
    public static String displayStatistics(int track, ArrayList<Competitor> competitors) {
        //return data member
        String data = "";
        //calculation data members
        int avgSeconds = -1;
        int sumSeconds = 0;
        int j;
        int skipCounter = 0;
        
        //Loop throguh competitors
        for (j = 0; j < Competitor.getNumCompetitors(); j++) {

            //makes those who have not performed time trial not be thrown into the calculations 
            if (competitors.get(j).getBestTimes(track) == 0) {
                // *important* this counter specifically will be used 
                // to adjust the calculations to account for
                // individuals that are overlooked
                skipCounter++;
                continue;
            }
            // set integer value seconds to the int value in the track index of the bestTimes array for the current Competitor instance
            // (representing their time for that track)
            int seconds = competitors.get(j).getBestTimes(track);
            // sum up the seconds for each competitor using variable on above line.
            sumSeconds += seconds;

        }
        // if there exists at least 1 competitor and they have not been skipped...
        if (j > 0 && skipCounter < j) {
            //calculate and average
            avgSeconds = sumSeconds / (j - skipCounter);

        }
        // Switch changes trackName Strin used in formatted print based on the integer passed by the track variable
        String trackName = null;
        // Tracks represented by integer 1-4 inclusive, Sunshine Airport, Dolphin Shoals, Electrodome, and Mount Wario respectively.
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
        // If avgSEconds never updated, then no time trials were recorded. Returns that info as string if true.
        if (avgSeconds == -1) {
            data += "no time trials recorded";
        }
        // Otherwise, formatted print with track name and average time is returned instead.
        else {
            data += String.format("Average time for %s: %s", trackName, formatTime(avgSeconds));
        }

        return data;
    }

    // formatTime is used to format time into the following format: M'S" where M represents an integer for minutes
    // and S represents an integer for seconds.
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