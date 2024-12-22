package hust.soict.dsai.javafx;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penTool;

    @FXML
    private RadioButton eraserTool;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider brushSizeSlider;

    private ToggleGroup toolsGroup;

    @FXML
    public void initialize() {
        toolsGroup = new ToggleGroup();
        penTool.setToggleGroup(toolsGroup);
        eraserTool.setToggleGroup(toolsGroup);
    }

    @FXML
    private void clearButtonPressed() {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    private void drawingAreaMouseDragged(MouseEvent event) {
        double brushSize = brushSizeSlider.getValue();
        if (penTool.isSelected()) {
            Circle circle = new Circle(event.getX(), event.getY(), brushSize / 2);
            circle.setFill(colorPicker.getValue());
            drawingAreaPane.getChildren().add(circle);
        } else if (eraserTool.isSelected()) {
            Circle eraser = new Circle(event.getX(), event.getY(), brushSize / 2);
            eraser.setFill(Color.WHITE);
            drawingAreaPane.getChildren().add(eraser);
        }
    }

    @FXML
    private void saveCanvas() {
        WritableImage image = new WritableImage((int) drawingAreaPane.getWidth(), (int) drawingAreaPane.getHeight());
        drawingAreaPane.snapshot(null, image);
        File file = new File("drawing.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
