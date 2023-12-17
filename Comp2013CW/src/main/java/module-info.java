module Comp2013CW {

    opens example;
    opens example.Settings;
    opens example.Utilities;
    opens example.LeaderBoard;
    opens example.SnakeGame;
    opens example.SnakeGame.Model;
    opens example.SnakeGame.Model.GameObjects;
    opens example.SnakeGame.Model.GameObjects.FoodObjects;
    opens example.SnakeGame.Model.LevelManager;

    requires java.desktop;
    requires jlayer;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.media;

}