package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    // Constructor
    public CompactDisc(int id, String title, String category, float cost, String artist, String director) {
        super(id, title, category, cost, 0, director);
        this.artist = artist;
    }

    // Getter cho artist
    public String getArtist() {
        return artist;
    }

    // Thêm track vào danh sách
    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track already exists: " + track.getTitle());
        } else {
            tracks.add(track);
        }
    }

    // Xóa track khỏi danh sách
    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
        } else {
            System.out.println("Track not found: " + track.getTitle());
        }
    }

    // Tính tổng độ dài của CD
    @Override
    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }

    // Implement phương thức play()
    @Override
    public void play() {
        if (getLength() > 0) {
            System.out.println("Playing CD: " + getTitle());
            System.out.println("Artist: " + artist);
            System.out.println("Total length: " + getLength());
            for (Track track : tracks) {
                track.play(); // Gọi phương thức play() của từng track
            }
        } else {
            System.out.println("CD " + getTitle() + " cannot be played due to invalid length.");
        }
    }
}
