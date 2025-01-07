public class Track{
    private String name;
    private String song;
    private String artist;
    private int releaseYear;
    private String genre;
    private String link;
    private int songLength; //in seconds
    
    public Track(String name, String song, String artist, int releaseYear, String genre, String link, int songLength){
        this.name = name;
        this.song = song;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.link = link;
        this.songLength = songLength;
    }
    
    public String getName(){
        return name;
    }
    
    public String getSong(){
        return song;
    }
    
    public String getArtist(){
        return artist;
    }
    
    public int getReleaseYear(){
        return releaseYear;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public String getLink(){
        return link;
    }
    
    public int getSongLength(){
        return songLength; 
    }
    
    
    
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d, %s, %s, %d seconds", name, song, artist, releaseYear, genre, link, songLength);
    }
}

