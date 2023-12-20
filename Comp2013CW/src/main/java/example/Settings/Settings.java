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

/**
 * The Settings class contains static methods and properties for managing and customizing settings,
 * such as colors, fonts, and paths used in the game application.
 */
public class Settings {
    //Set Color for Testing Purposes
    protected static Color primaryColor = Color.RED ;
    protected static Color secondaryColor = Color.BLACK;
    protected static int textSize = 19;

    protected static Font font = Font.font("Calibri");

    public static FontWeightEnum fontWeight = FontWeightEnum.NORMAL;
    public enum FontWeightEnum {
        NORMAL,
        BOLD,
        BOLDER,
        LIGHTER
    }


    public BackgroundFill backgroundFill;
    public static String SnakeHeadPath = "snake-head-default.png";
    public static String SnakeBodyPath = "snake-body-default.png";

    public static String SnakeHeadLocation = "/Images/Snake_Images/Snake_Heads/";
    public static String SnakeBodyLocation = "/Images/Snake_Images/Snake_Bodys/";

    protected static String CSSFormat;
    public static String getCSSFormat(){ return CSSFormat;}

    //Util Function to Get Snake Path

    public static String ReturnSnakeHeadPath(){return SnakeHeadLocation + SnakeHeadPath;}
    public static String ReturnSnakeHeadName(){return SnakeHeadPath.substring(11, SnakeHeadPath.lastIndexOf("."));}
    public static String ReturnSnakeBodyPath(){ return  SnakeBodyLocation + SnakeBodyPath; }
    public static String ReturnSnakeBodyName(){return SnakeBodyPath.substring(11, SnakeBodyPath.lastIndexOf("."));}

    /**
     * Returns the formatted CSS for background fill using primary and secondary colors.
     *
     * @return The formatted CSS for background fill.
     */
    public static  Background ReturnBackgroundFill(){
        Stop[] stops = {
                new Stop(0, primaryColor),
                new Stop(1, secondaryColor)
        };

        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, null, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, null, null);
        return new Background(backgroundFill);

    }
    /**
     * Creates a styled button with the specified text based on current settings.
     *
     * @param text The text to be displayed on the button.
     * @return The styled button.
     */
    public static Button createStyledButton(String text) {
        Button button = new Button(text);
        String CSSFormat = "-fx-background-color: " + getHex(primaryColor) + ";" +
                "-fx-text-fill: " + getHex(secondaryColor) + ";" +
                " -fx-font-size: " + textSize + "px;" +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                " -fx-font: " + font + ";" +
                "-fx-border-color: " + getHex(secondaryColor) + ";"+
                "-fx-border-width: 2px;";

        button.setStyle(CSSFormat);
        return button;
    }


    /**
     * Creates a styled ComboBox with the specified data based on current settings.
     *
     * @param comboBoxData The data for populating the ComboBox.
     * @param <T>          The type of data in the ComboBox.
     * @return The styled ComboBox.
     */
    public static <T> ComboBox<T> createComboBox(ObservableList<T> comboBoxData) {
        String CSSFormat = "-fx-background-color: " + getHex(primaryColor) + ";" +
                "-fx-text-fill: " + getHex(secondaryColor) + ";" +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                "-fx-font-size: " + textSize + "px;" +
                "-fx-font: " + fontWeight.toString() + " " + font + ";" +
                "-fx-border-color: " + getHex(secondaryColor) + ";" +
                "-fx-border-width: 2px;";

        ComboBox<T> comboBox = new ComboBox<>(comboBoxData);
        comboBox.setStyle(CSSFormat);
        return comboBox;
    }

    /**
     * Creates a styled Label with the specified text based on current settings.
     *
     * @param text The text to be displayed on the label.
     * @return The styled Label.
     */
    public static Label createLabel(String text) {
        Label label = new Label(text);
        String CSSFormat = "-fx-text-fill: " + getHex(secondaryColor) + "; " +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                "  -fx-font-size: " + textSize + ";" ;
        label.setStyle(CSSFormat);
        return label;
    }

    /**
     * Creates a styled Label with the specified text and size based on current settings.
     *
     * @param text     The text to be displayed on the label.
     * @param textSize The size of the text on the label.
     * @return The styled Label.
     */
    public static Label createLabel(String text , int textSize) {
        Label label = new Label(text);
        String CSSFormat = "-fx-text-fill: " + getHex(secondaryColor) + "; " +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
                "  -fx-font-size: " + textSize + ";" ;
        label.setStyle(CSSFormat);
        return label;
    }

    /**
     * Creates a styled ColorPicker with the specified initial color based on current settings.
     *
     * @param setColor The initial color for the ColorPicker.
     * @return The styled ColorPicker.
     */
    public static ColorPicker createColorPicker(Color setColor) {
        String CSSFormat = "-fx-background-color: " + getHex(primaryColor) + ";" +
            "-fx-text-fill: " + getHex(secondaryColor) + ";" +
            " -fx-font-size: " + textSize + "px;" +
                "-fx-font-weight: " + fontWeight.toString() + ";" +
            " -fx-font: " + font + ";" +
            "-fx-border-color: " + getHex(secondaryColor) + ";"+
            "-fx-border-width: 2px;";
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setStyle(CSSFormat);
        colorPicker.setValue(setColor);

        return colorPicker;
    }
    /**
     * Creates a Slider with tick marks and labels for adjusting text size.
     *
     * @return The created Slider.
     */
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

    /**
     * Creates a styled TableView for displaying score entries.
     *
     * @param data The ObservableList containing score entries.
     * @return The styled TableView.
     */
    public static TableView<ScoreEntry> createTableView(ObservableList<ScoreEntry> data) {

        String CSSFormat = "-fx-background-color: " + getHex(primaryColor) + ";" +
                "-fx-text-fill: " + getHex(secondaryColor) + ";" +
                "-fx-font-size: " + textSize + "px;" +
                "-fx-border-color: " + getHex(secondaryColor) + ";"
                ;

        TableView<ScoreEntry> tableView = new TableView<>(data);
        tableView.setStyle(CSSFormat);
        return tableView;

    }

    /**
     * Creates a styled TableColumn for the TableView with the specified username.
     *
     * @param username The username for the TableColumn.
     * @param <T>      The type of data in the TableColumn.
     * @return The styled TableColumn.
     */
    public static <T> TableColumn<ScoreEntry, T > createTableColumn(String username) {
        String CSSFormat = "-fx-background-color: " + getHex(primaryColor) + ";" +
                "-fx-text-fill: " + getHex(secondaryColor) + ";" +
                "-fx-font-size: " + textSize + "px;" +
                "-fx-border-color: " + getHex(secondaryColor) + ";" +
                "-fx-border-width: 2px;";

        TableColumn<ScoreEntry, T> column = new TableColumn<>(username);
        column.setStyle(CSSFormat);
        return  column;
    }

    /**
     * Converts a Color object to its hexadecimal representation.
     *
     * @param color The Color object to convert.
     * @return The hexadecimal representation of the color.
     */
    public static String getHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255)
        );
    }
//Testing Utilities
    public static void setTestSettings(){
        primaryColor = Color.RED ;
        secondaryColor = Color.BLACK;
        textSize = 19;
        font = Font.font("Calibri");
        fontWeight = FontWeightEnum.NORMAL;

    }

    public static Color getPrimaryColor() {
        return primaryColor;
    }

    public static Color getSecondaryColor() {
        return secondaryColor;
    }

    public static Font getFont() {
        return font;
    }

    public static int getTextSize() {
        return textSize;
    }

    public static FontWeightEnum getFontWeight() {
        return fontWeight;
    }
}
