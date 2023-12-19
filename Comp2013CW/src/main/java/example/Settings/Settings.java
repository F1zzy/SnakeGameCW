package example.Settings;

import example.LeaderBoard.ScoreEntry;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

import javax.swing.text.html.CSS;


public class Settings {
    public static Color PrimaryColor ;
    public static Color SecondaryColor;

    public static int TextSize;

    public static Font font;

    public static TableView<ScoreEntry> createTableView(ObservableList<ScoreEntry> data) {

        String CSSFormat = "-fx-background-color: " + getHex(PrimaryColor) + ";" +
                "-fx-text-fill: " + getHex(SecondaryColor) + ";" +
                "-fx-font-size: " + TextSize + "px;" +
                "-fx-border-color: " + getHex(SecondaryColor) + ";"
                ;

        TableView<ScoreEntry> tableView = new TableView<>(data);
        tableView.setStyle(CSSFormat);
        return tableView;

    }

    public static <T> TableColumn<ScoreEntry, T > createTableColumn(String username) {
        String CSSFormat = "-fx-background-color: " + getHex(PrimaryColor) + ";" +
                "-fx-text-fill: " + getHex(SecondaryColor) + ";" +
                "-fx-font-size: " + TextSize + "px;" +
                "-fx-border-color: " + getHex(SecondaryColor) + ";" +
                "-fx-border-width: 2px;";

        TableColumn<ScoreEntry, T> column = new TableColumn<>(username);
        column.setStyle(CSSFormat);
        return  column;
    }

    public enum FontWeightEnum {
        NORMAL,
        BOLD,
        BOLDER,
        LIGHTER
    }
    public static FontWeightEnum fontWeight;

    public BackgroundFill backgroundFill;
    public static String SnakeHeadPath = "snake-head-default.png";
    public static String SnakeBodyPath = "snake-body-default.png";

    public static String SnakeHeadLocation = "/Images/Snake_Images/Snake_Heads/";
    public static String SnakeBodyLocation = "/Images/Snake_Images/Snake_Bodys/";


    public static String CSSFormat;

    public static String ReturnSnakeHeadPath(){return SnakeHeadLocation + SnakeHeadPath;}
    public static String ReturnSnakeHeadName(){return SnakeHeadPath.substring(10, SnakeHeadPath.lastIndexOf("."));}
    public static String ReturnSnakeBodyPath(){ return  SnakeBodyLocation + SnakeBodyPath; }
    public static String ReturnSnakeBodyName(){return SnakeBodyPath.substring(10, SnakeBodyPath.lastIndexOf("."));}
    private static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255)
        );
    }
    public static  Background ReturnBackgroundFill(){
        Stop[] stops = {
                new Stop(0, PrimaryColor),
                new Stop(1, SecondaryColor)
        };

        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, null, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, null, null);
        return new Background(backgroundFill);

    }

    public static Button createStyledButton(String text) {
        Button button = new Button(text);
        String CSSFormat = "-fx-background-color: " + getHex(PrimaryColor) + ";" +
                "-fx-text-fill: " + getHex(SecondaryColor) + ";" +
                " -fx-font-size: " + TextSize + "px;" +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                " -fx-font: " + font + ";" +
                "-fx-border-color: " + getHex(SecondaryColor) + ";"+
                "-fx-border-width: 2px;";

        button.setStyle(CSSFormat);
        return button;
    }
    public static <T> ComboBox<T> createComboBox(ObservableList<T> comboBoxData) {
        String CSSFormat = "-fx-background-color: " + getHex(PrimaryColor) + ";" +
                "-fx-text-fill: " + getHex(SecondaryColor) + ";" +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                "-fx-font-size: " + TextSize + "px;" +
                "-fx-font: " + fontWeight.toString() + " " + font + ";" +
                "-fx-border-color: " + getHex(SecondaryColor) + ";" +
                "-fx-border-width: 2px;";

        ComboBox<T> comboBox = new ComboBox<>(comboBoxData);
        comboBox.setStyle(CSSFormat);
        return comboBox;
    }


    public static Label createLabel(String text) {
        Label label = new Label(text);
        String CSSFormat = "-fx-text-fill: " + getHex(SecondaryColor) + "; " +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                "  -fx-font-size: " + TextSize + ";" ;
        label.setStyle(CSSFormat);
        return label;
    }
    public static Label createLabel(String text , int textSize) {
        Label label = new Label(text);
        String CSSFormat = "-fx-text-fill: " + getHex(SecondaryColor) + "; " +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                "  -fx-font-size: " + textSize + ";" ;
        label.setStyle(CSSFormat);
        return label;
    }

    public static ColorPicker createColorPicker(Color setColor) {
        String CSSFormat = "-fx-background-color: " + getHex(PrimaryColor) + ";" +
            "-fx-text-fill: " + getHex(SecondaryColor) + ";" +
            " -fx-font-size: " + TextSize + "px;" +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
            " -fx-font: " + font + ";" +
            "-fx-border-color: " + getHex(SecondaryColor) + ";"+
            "-fx-border-width: 2px;";
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setStyle(CSSFormat);
        colorPicker.setValue(setColor);

        return colorPicker;
    }

    public static Slider createTextSizeSlider() {
        Slider slider = new Slider();
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(5);
        slider.setMinorTickCount(4);
        slider.setBlockIncrement(1);
        return slider;
    }

    private static String getHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255)
        );
    }
}
