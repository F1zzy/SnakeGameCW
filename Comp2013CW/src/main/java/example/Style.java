package example;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

public class Style {
    static Color PrimaryColor = Color.RED;
    static Color SecondaryColor = Color.BLACK;

    int TextSize;

    Font font;

    BackgroundFill backgroundFill;

    static String CSSFormat;
    public static void init() {
        Color PrimaryColor = Color.RED;
        Color SecondaryColor = Color.BLACK;

        int TextSize = 18;

        Font font = Font.font("Calibri");

        BackgroundFill backgroundFill;

        CSSFormat = String.format(
                "-fx-background-color: %s; -fx-text-fill: %s; -fx-font-size: %dpx; -fx-font-family: '%s'; -fx-border-color: %s; -fx-border-width: 2px;",
                toHex(PrimaryColor), toHex(SecondaryColor), TextSize, font.getFamily(), toHex(SecondaryColor)
        );


    }
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

    static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(CSSFormat);
        return button;
    }
}
