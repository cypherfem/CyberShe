package com.ajith.practice;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.media.MediaView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BiasApp extends Application {
    private MediaPlayer musicPlayer;

    @Override
    public void start(Stage primaryStage) {


       //buttons for laptop
        Button analyzerApp = new Button("Bias Analyzer");
        Button videos = new Button("Videos");
        Button newsApp = new Button("News");

        //creates a Vbox, to verticallly align the desktop icons, top to bottoms
        VBox desktopIcons = new VBox(10);

        //adding children (apps) into the desktopIcons Vbox
        desktopIcons.getChildren().addAll(
                analyzerApp,
                videos,
                newsApp
        );


        Image backgroundImage =
                new Image("file:src/images/background.png");

        ImageView backgroundView =
                new ImageView(backgroundImage);

        StackPane amayaLayout = new StackPane();

        amayaLayout.getChildren().addAll(
                backgroundView,
                desktopIcons
        );


        Scene scene = new Scene(amayaLayout, 600, 400);

        // Make desktop background resize with the window
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
        backgroundView.setPreserveRatio(false);

        primaryStage.setTitle("amaya's computer :]");
        primaryStage.setScene(scene);

        Media music = new Media(
                new java.io.File("src/music/background.mp3")
                        .toURI()
                        .toString()
        );

        musicPlayer = new MediaPlayer(music);

        musicPlayer.setOnReady(() -> {
            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            musicPlayer.setVolume(1.0);
            musicPlayer.play();


        });


        primaryStage.show();

        // ---------- ANALYZER CONTROLS ----------

        TextArea sentenceBox = new TextArea();
        sentenceBox.setPromptText("Write a sentence to analyze...");
        sentenceBox.setWrapText(true);

        Label resultLabel = new Label();

        TextArea results = new TextArea();
        results.setEditable(false);
        results.setWrapText(true);

        Button analyzeButton = new Button("Calculate Bias");
        Button clearButton = new Button("Clear");


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


        analyzerApp.setOnAction(actionEvent -> {








            Button backButton = new Button("Back");
            Button xButton = new Button("X");






            VBox analyzerContents = new VBox(10);


            analyzerContents.getChildren().addAll(
                    sentenceBox,
                    analyzeButton,
                    resultLabel,
                    results,
                    clearButton,
                    xButton
            );


            StackPane analyzerLayout = new StackPane();


            analyzerLayout.getChildren().addAll(
                    analyzerContents
            );
            amayaLayout.getChildren().add(analyzerLayout);


            // Return to the desktop screen
            xButton.setOnAction(backEvent -> {
                amayaLayout.getChildren().remove(analyzerLayout);
            });
        });


        newsApp.setOnAction(actionEvent -> {

            Image womanLogo = new Image("file:src/images/womanLogo.png");
            ImageView womanLogoview = new ImageView(womanLogo);
            Button closeButton = new Button("X");
            Label authorLabel = new Label("By Able Easley • NASA Correspondent • July 24, 2070");

            Label newsTitle = new Label("HAS MAN GONE TOO FAR?");

            newsTitle.setStyle(
                    "-fx-font-size: 32px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: #202020;"
            );

            Label newsBrandTitle =
                    new Label("LOVELACE CYBERNETIC UNIVERSITY");

            newsBrandTitle.setStyle(
                    "-fx-font-size: 24px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: #202020;"
            );

            HBox logoAndTitle = new HBox(15);
            logoAndTitle.setAlignment(Pos.CENTER_LEFT);

            logoAndTitle.getChildren().addAll(
                    womanLogoview,
                    newsBrandTitle
            );

            BorderPane newsTopBar = new BorderPane();
            newsTopBar.setLeft(logoAndTitle);
            newsTopBar.setRight(closeButton);



            authorLabel.setStyle(
                    "-fx-font-size: 12px;" +
                            "-fx-text-fill: #666666;"
            );



            womanLogoview.setFitWidth(110);
            womanLogoview.setPreserveRatio(true);


            Image womanCyborg = new Image("file:src/images/womanCyborg.png");
            ImageView womanCyborgView = new ImageView(womanCyborg);

            womanCyborgView.setFitWidth(420);
            womanCyborgView.setPreserveRatio(true);

            StackPane cyborgPictureBox = new StackPane(womanCyborgView);


            Label articleText = new Label(
                    "TK."
            );

            articleText.setWrapText(true);

            articleText.setMaxWidth(650);

            articleText.setStyle(
                    "-fx-font-size: 16px;" +
                            "-fx-text-fill: #303030;" +
                            "-fx-line-spacing: 5px;"
            );


            newsTopBar.setLeft(womanLogoview);
            newsTopBar.setRight(closeButton);

            VBox newsLayout = new VBox(10);
            newsLayout.setPadding(new Insets(20));
            newsLayout.setStyle("-fx-background-color: white;");

            newsLayout.getChildren().addAll(
                    newsTopBar,
                    newsTitle,
                    authorLabel,
                    cyborgPictureBox,
                    articleText
            );

            ScrollPane articleScroll = new ScrollPane(newsLayout);
            articleScroll.setFitToWidth(true);
            articleScroll.setPrefSize(700, 500);
            articleScroll.setMaxSize(700, 500);

            StackPane newsArticalelayout = new StackPane(articleScroll);
            newsArticalelayout.setPrefSize(700, 500);
            newsArticalelayout.setMaxSize(700, 500);

            amayaLayout.getChildren().add(newsArticalelayout);

            closeButton.setOnAction(closeEvent -> {
                amayaLayout.getChildren().remove(newsArticalelayout);
            });





        });

        videos.setOnAction(actionEvent1 -> {
            Media secretAd = new Media(
                    new java.io.File("src/videos/secret_ad.mp4")
                            .toURI()
                            .toString()
            );

            MediaPlayer cyberFemplayer = new MediaPlayer(secretAd);
            MediaView videoView = new MediaView(cyberFemplayer);

            videoView.setFitWidth(680);
            videoView.setFitHeight(420);
            videoView.setPreserveRatio(true);
            amayaLayout.getChildren().add(videoView);
            cyberFemplayer.play();

        });

        Image videoIcon = new Image("src/images/video_icon.png");
        ImageView videoIconview = new ImageView(videoIcon);

        videoIconview.setFitWidth(60);
        videoIconview.setPreserveRatio(true);

        Label videoLabel = new Label("videos");

        videoLabel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-size: 16px;"
        );
        VBox videoIconpic = new VBox(10);

        videoIconpic.setAlignment(Pos.CENTER);
        videoIconpic.getChildren().addAll(videoIconview, videoLabel);

    }

    public static void main(String[] args) {
        launch(args);
    }
}