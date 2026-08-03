package com.ajith.practice;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.ImageCursor;
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
        Button womanosphere = new Button("womanosphere");


        //creates a Vbox, to verticallly align the desktop icons, top to bottoms
        VBox desktopIcons = new VBox(10);

        //adding children (apps) into the desktopIcons Vbox
        desktopIcons.getChildren().addAll(
                analyzerApp,
                womanosphere
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
        StackPane.setAlignment(desktopIcons, Pos.TOP_LEFT);

        StackPane.setMargin(
                desktopIcons,
                new Insets(20)
        );

        Scene scene = new Scene(amayaLayout, 600, 400);

        Image cursorImage =
                new Image("file:src/images/pink_cursor.png");

        ImageCursor pinkCursor =
                new ImageCursor(cursorImage, 0, 0);

        scene.setCursor(pinkCursor);
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


        Image newsIconImage =
                new Image("file:src/images/news_icon.png");

        ImageView newsIconView =
                new ImageView(newsIconImage);

        newsIconView.setFitWidth(60);
        newsIconView.setPreserveRatio(true);

        Label newsLabel = new Label("news");

        newsLabel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;"
        );

        VBox newsIconBox = new VBox(10);
        newsIconBox.setAlignment(Pos.CENTER_LEFT);

        newsIconBox.getChildren().addAll(
                newsIconView,
                newsLabel
        );

        desktopIcons.getChildren().add(newsIconBox);

        newsIconBox.setOnMouseClicked(actionEvent -> {

            Image news = new Image("file:src/images/news.png");
            ImageView newsView = new ImageView(news);

            newsView.setFitWidth(900);
            newsView.setPreserveRatio(true);

            ScrollPane newsScrollPane = new ScrollPane(newsView);

            // Size of the news window, not the entire scre

            newsScrollPane.setPrefSize(750, 650);
            newsScrollPane.setMaxSize(750, 650);


            newsScrollPane.setPannable(true);

            // Centers the image inside the ScrollPane
            newsScrollPane.setFitToWidth(false);

            // Makes the area around the article transparent
            newsScrollPane.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-background: transparent;"
            );

            // Centers the entire news window on the desktop
            StackPane.setAlignment(newsScrollPane, Pos.CENTER);

            amayaLayout.getChildren().add(newsScrollPane);

        });





        Image videoIcon = new Image("file:src/images/video_icon.png");
        ImageView videoIconview = new ImageView(videoIcon);

        videoIconview.setFitWidth(60);
        videoIconview.setPreserveRatio(true);

        Label videoLabel = new Label("videos");

        videoLabel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;"
        );
        VBox videoIconpic = new VBox(10);

        videoIconpic.setAlignment(Pos.CENTER_LEFT);
        videoIconpic.getChildren().addAll(videoIconview, videoLabel);
        desktopIcons.getChildren().add(videoIconpic);

        videoIconpic.setOnMouseClicked(event -> {


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

        womanosphere.setOnAction(actionEvent1 -> {

            Image womansphereImage = new Image("file:src/images/home.png");
            ImageView womanSphereview = new ImageView(womansphereImage);
            womanSphereview.setFitWidth(610);
            womanSphereview.setFitHeight(550);

            Pane womanSpherelayout = new Pane();
            womanSpherelayout.getChildren().add(womanSphereview);
            womanSpherelayout.setPrefSize(610, 550);

            Button home = new Button();
            home.setLayoutX(194);
            home.setLayoutY(81);
            home.setPrefSize(120, 44);
            home.setStyle("-fx-background-color: transparent;");

            Button amayaPost = new Button();
            amayaPost.setLayoutX(335);
            amayaPost.setLayoutY(81);
            amayaPost.setPrefSize(120, 44);
            amayaPost.setStyle("-fx-background-color: transparent;");


            Button herEssay = new Button();
            herEssay.setLayoutX(475);
            herEssay.setLayoutY(81);
            herEssay.setPrefSize(120, 44);
            herEssay.setStyle("-fx-background-color: transparent;");

            Image amayaPostimage = new Image("file:src/images/blog.png");
            Image essayImage = new Image("file:src/images/essay.png");

            home.setOnAction(event -> {
                womanSphereview.setImage(womansphereImage);
            });

            amayaPost.setOnAction(event -> {
                womanSphereview.setImage(amayaPostimage);
            });

            herEssay.setOnAction(event -> {
                womanSphereview.setImage(essayImage);
            });

            womanSpherelayout.getChildren().addAll(
                    home,
                    amayaPost,
                    herEssay
            ) ;
            womanSpherelayout.setPrefSize(610, 550);
            womanSpherelayout.setMaxSize(610, 550);
            amayaLayout.getChildren().add(womanSpherelayout);

        });



    }

    public static void main(String[] args) {
        launch(args);
    }
}