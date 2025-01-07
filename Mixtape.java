import java.util.*;
import java.io.*;
public class Mixtape{
    
    private List<Track> tracks;
    private int mixtapeLength;
    private int MAX_MIXTAPE_LENGTH = 30*60; //30 minutes converted to seconds 
    private int PAUSE = 2; // two second standard pause between songs
    
    public Mixtape(){
        tracks = new ArrayList<Track>();
        mixtapeLength = 0;
    }
    
    public Mixtape(List<Track> inputtracks){
        tracks = inputtracks;
        mixtapeLength = 0;
        for (Track track: inputtracks){
            mixtapeLength += track.getSongLength() + PAUSE;
        }
    }
    
    public int getMixtapeLength(){
        return mixtapeLength;
    }
    
    public int getNumberOfTracks(){
        return tracks.size();
    }
    
    public Track getTrack(int index){
        return tracks.get(index);
    }
    
    public List<Track> getAllTracks(){
        return tracks;
    }
    
    public void addTrack(Track track){
        tracks.add(track);
        mixtapeLength += track.getSongLength();
    }
    
    public Mixtape createSubMixtape(String genre){
        List<Track> playlist = new ArrayList<Track>();
        int playlistLength = 0;
        
        for (Track track : tracks) {
            if (track.getGenre().equalsIgnoreCase(genre) && (playlistLength + track.getSongLength() + PAUSE)<MAX_MIXTAPE_LENGTH){
                playlist.add(track);
                playlistLength += track.getSongLength() + PAUSE;
            }
        }
        return new Mixtape(playlist);
        
    }
    
    public Track findTrackByName(String songName) {
        for (Track track : tracks) {
            if (track.getSong().equalsIgnoreCase(songName)) {
                return track;
            }
        }
        return null;
    }

}
