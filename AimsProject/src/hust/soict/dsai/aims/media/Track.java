package hust.soict.dsai.aims.media;

import java.util.Objects;

public class Track implements Playable {
    private String title;
    private int length;

    // Constructor
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    // Override equals() để so sánh hai track
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return length == track.length && title.equals(track.title);
    }


    // Implement phương thức play()
    @Override
    public void play() {
        if (length > 0) {
            System.out.println("Playing track: " + getTitle());
            System.out.println("Track length: " + getLength());
        } else {
            System.out.println("Track " + getTitle() + " cannot be played due to invalid length.");
        }
    }
     
}
