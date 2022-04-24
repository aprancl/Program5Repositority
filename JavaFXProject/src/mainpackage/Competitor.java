package mainpackage;



public class Competitor {
    //Data Members
    private int id;
    private String firstName;
    private String lastName;
    private String character;
    private String vehicle;
    private int[] bestTimes;
    public static int numCompetitors;

    // Constructors 
    public Competitor() { // default
        numCompetitors++;
        bestTimes = new int[4];

    }

    public Competitor(int id, String firstName, String lastName, String character, String vehicle) { // all vars constructors
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.character = character;
        this.vehicle = vehicle;
        numCompetitors++;
        bestTimes = new int[4];
    }

    // GETTERS 
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCharacter() {
        return character;
    }

    public String getVehicle() {
        return vehicle;
    }

    public int getBestTimes(int track) {
        return bestTimes[track];
    }

    public static int getNumCompetitors() {
        return numCompetitors;
    }
    
    public String getFullName(){
        return (this.getFirstName() + " " + this.getLastName());
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setBestTimes(int track, int time) {
        bestTimes[track] = time;

    }

    // Other methods
    
    // used to output data for a particular instance of this class 
    @Override
    public String toString() {
        String data = ""; // empty string to continually add on two 

        data += String.format("   %s %s (ID: %d)\n", firstName, lastName, id);
        data += String.format("   Character: %s\n", character);
        data += String.format("   Vehicle:   %s\n", vehicle);
        data += "   ***Best Times***\n";
        // temporary vars to change tepending on different circumstances
        String tempSS = (bestTimes[0] == 0) ? "no time recorded" : String.valueOf(formatTime(bestTimes[0]));
        String tempDS = (bestTimes[1] == 0) ? "no time recorded" : String.valueOf(formatTime(bestTimes[1]));
        String tempED = (bestTimes[2] == 0) ? "no time recorded" : String.valueOf(formatTime(bestTimes[2]));
        String tempMW = (bestTimes[3] == 0) ? "no time recorded" : String.valueOf(formatTime(bestTimes[3]));
        // ---
        data += String.format("      Sunshine Airport: %s\n", tempSS);
        data += String.format("      Dolphin Shoals:   %s\n", tempDS);
        data += String.format("      Electrodrome:     %s\n", tempED);
        data += String.format("      Mount Wario:      %s\n", tempMW);

        return data;
    }
    
    

    
    // Other Methods not explicitly in UML Diagram
    
    // effectively a toString() method intended for displayLeaderBoard() method
    public String toLeaderboardString(int track) {
        String data = "";
        data += String.format("      %s %s (ID: %d)\n", firstName, lastName, id);
        data += String.format("      Character: %s\n", character);
        data += String.format("      Vehicle:   %s\n", vehicle);
        String time = String.valueOf(formatTime(bestTimes[track]));
        data += String.format("      Time:      %s", time);
        
        return data;
    }
    
    //Useful method for outputing info on time
    private String formatTime(int seconds) {

        // calculate minutes and seconds
        int minutes = seconds / 60; // get minutes 
        seconds = seconds % 60; // keep track of remainder seocnds 
        String formattedTime;

        // when and when not to place a zero in front of the seconds 
        if (seconds < 10) {
            formattedTime = "" + minutes + "'0" + seconds + "\"";
        }
        else {
            formattedTime = "" + minutes + "'" + seconds + "\"";
        }

        return formattedTime;
    }

}//END of Competitor CLASS


