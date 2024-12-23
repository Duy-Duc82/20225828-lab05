package hust.soict.dsai.aims.screen;

import javax.swing.JFrame;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.screen.controller.AddTrackScreenController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddTrack extends JFrame {

    private static CompactDisc cd;

    public static void main(String[] args) {
        CompactDisc cd = new CompactDisc("", "", "", 0.0f); // Example placeholder CompactDisc
        new AddTrack(cd);
    }

    public AddTrack(CompactDisc cd) {

        super();

        AddTrack.cd = cd;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Add Tracks");
        this.setSize(638, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/addTracks.fxml"));

                AddTrackScreenController controller = new AddTrackScreenController(cd);
                loader.setController(controller);
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}