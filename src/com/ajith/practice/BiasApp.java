package com.ajith.practice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BiasApp extends Application {

    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("amaya's computer :]");

        button = new Button();
        button.setText("click here");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        VBox appIcons = new VBox();
        appIcons.getChildren().add(button);
        appIcons.getChildren().add(button);
        appIcons.getChildren().add(button);
        appIcons.getChildren().add(button);



        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
