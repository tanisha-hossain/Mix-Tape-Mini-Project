import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        String filePath = "data.csv";
        //List<Track> mixtape = new ArrayList<>();
        Mixtape mixtape = new Mixtape();

        //read file using Scanner
        try  {
            File myFile = new File(filePath);
            Scanner scanner = new Scanner(myFile);
            
            
            if (myFile.exists()) {
                System.out.println("File name: " + myFile.getName());
                System.out.println("File size in bytes " + myFile.length());
                // print out first line which is header
                System.out.println("Header: " + scanner.nextLine());
            } 
            else {
                System.out.println("The file does not exist.");
            }
            
            if(!scanner.hasNext()==true){
                System.out.println("Error: File is empty");
            }
            
      
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String[] values = line.split(",");
                //ensure any blank lines in data don't cause out of bounds error
                if (values.length <7) {
                    System.out.println("bad");
                    continue;
                }
                String name = values[0].trim();
                String songName = values[1].trim();
                String artist = values[2].trim();
                int releaseYear = Integer.parseInt(values[3].trim());
                String genre = values[4].trim();
                String link = values[5].trim();
                //int songLength = Integer.parseInt(values[6].trim());
                int songLength = convertToSeconds(values[6].trim());
                
                Track song = new Track(name, songName, artist, releaseYear, genre, link, songLength);
                mixtape.addTrack(song);
                
                }
                
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing year: " + e.getMessage());
        }

        // Display all songs
        System.out.println("Total songs:" + mixtape.getNumberOfTracks());
        for (Track track : mixtape.getAllTracks()) {
            System.out.println(track);
        }
        
        System.out.println("\nMake your own sub-mixtape from the songs above! Do you like Rock, Pop, Rap, R and B, or Classical music?");
        System.out.print("I like: ");
        Scanner user = new Scanner(System.in);
        String chosenGenre = user.nextLine();
        Mixtape usersMixtape = mixtape.createSubMixtape(chosenGenre);
        
        for (Track track : usersMixtape.getAllTracks()) {
            System.out.printf("\n%s | %s | %s | %d | %s | %s | %d seconds", track.getName(), track.getSong(), track.getArtist(), track.getReleaseYear(), track.getGenre(), track.getLink(), track.getSongLength());
        }
        System.out.println("\n\nLength of new mixtape (cannot be longer than 30 min): " + usersMixtape.getMixtapeLength() + " seconds");
        
        //compare release years
        System.out.println("\nAren't you curious about how long ago these amazing songs were released? Let's compare the release years of any two songs from the original mixtape!");
        user = new Scanner(System.in);
        System.out.print("Enter first song's title: ");
        String firstSong = user.nextLine().trim();
        System.out.print("Enter second song's title: ");
        String secondSong = user.nextLine().trim();

        Track track1 = mixtape.findTrackByName(firstSong);
        Track track2 = mixtape.findTrackByName(secondSong);
        if (track1 != null && track2 != null) {
            compareReleaseYears(track1, track2);
        } 
        else {
            System.out.println("Song(s) not found.");
        }
    } //end main
    
    public static void compareReleaseYears(Track trackA, Track trackB) {
        int yearDifference = Math.abs(trackA.getReleaseYear() - trackB.getReleaseYear());
        System.out.printf("\nThe difference in release years between %s and %s is %d years.", trackA.getSong(), trackB.getSong(), yearDifference);
    }
    
    
    public static int convertToSeconds(String input) {
        String[] parts = input.replace(":", " ").replace(".", " ").split(" "); //replace method changes the colons and commas to spaces
        int totalSeconds = 0;

        try {
            if (parts.length == 1) { 
                totalSeconds = (int) Double.parseDouble(parts[0]);
            } else if (parts.length == 2) {
                if (parts[1].contains("s")) { //for times that have the word "seconds" after
                    totalSeconds = (int) Double.parseDouble(parts[0]);
                } else {
                    // for time formats of minutes:seconds or minutes.seconds
                    int minutes = Integer.parseInt(parts[0]);
                    int seconds = Integer.parseInt(parts[1]);
                    totalSeconds = minutes * 60 + seconds;
                }
            } else if (parts.length == 3 && parts[2].contains("min")) { //if time has "minutes" written after
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                totalSeconds = minutes * 60 + seconds;
            }
        } catch (NumberFormatException e) {
            //System.out.println("Invalid format: " + input);
            totalSeconds = 0; 
        }

        return totalSeconds;
    }
    
} // end class

