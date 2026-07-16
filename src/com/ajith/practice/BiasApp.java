package com.ajith.practice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BiasApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create the desktop app buttons
        Button analyzerApp = new Button("Bias Analyzer");
        Button researchNotes = new Button("Research Notes");
        Button messagesApp = new Button("Messages");

        // Create the desktop layout
        VBox desktopIcons = new VBox(10);

        desktopIcons.getChildren().addAll(
                analyzerApp,
                researchNotes,
                messagesApp
        );

        // The scene initially displays the desktop layout
        Scene scene = new Scene(desktopIcons, 600, 400);

        primaryStage.setTitle("amaya's computer :]");
        primaryStage.setScene(scene);
        primaryStage.show();

        TextArea sentenceBox = new TextArea();
        Label resultLabel = new Label();
        TextArea results = new TextArea();
        results.setEditable(false);

        sentenceBox.setPromptText("Enter a sentence...");
        Button analyzeButton = new Button("Analyze");
        Button clearButton = new Button("Clear");

        clearButton.setOnAction(actionEvent -> {
            sentenceBox.clear();
            resultLabel.setText("");
            results.clear();
        });


        analyzeButton.setOnAction(event -> {
            String sentence = sentenceBox.getText();

            resultLabel.setText("");
            results.clear();

            if (sentence.isBlank()) {
                resultLabel.setText("Please input a sentence!");

            } else {
                String analysisResult = Main.analyzeSentence(sentence);
                results.setText(analysisResult);
            }
        });

        // Runs when the Bias Analyzer button is clicked
        analyzerApp.setOnAction(actionEvent -> {




            Button backButton = new Button("Back");



            VBox analyzerLayout = new VBox(10);

            analyzerLayout.getChildren().addAll(
                    sentenceBox,
                    analyzeButton,
                    resultLabel,
                    results,
                    clearButton,
                    backButton
            );

            // Replace the desktop with the analyzer screen
            scene.setRoot(analyzerLayout);

            // Return to the desktop screen
            backButton.setOnAction(backEvent -> {
                scene.setRoot(desktopIcons);
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}