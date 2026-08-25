package com.ajith.practice;

import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
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


        //creates a Vbox, to verticallly align the desktop icons, top to bottoms
        VBox desktopIcons = new VBox(10);
        desktopIcons.setFillWidth(false);





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

        primaryStage.setTitle("prayer's computer :]");
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
                String aiResult = Main.analyzeWithAI(sentence);

                results.setText(
                        analysisResult +
                                "\n\n--- AI ANALYSIS ---\n" +
                                aiResult);
            }
        });
        Image analyzerIconImage =
                new Image("file:src/images/analyzer_icon.png");

        ImageView analyzerIconView =
                new ImageView(analyzerIconImage);

        analyzerIconView.setFitWidth(60);
        analyzerIconView.setPreserveRatio(true);

        Label analyzerLabel = new Label("Bias Analyzer");

        analyzerLabel.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;"
        );

        VBox analyzerApps = new VBox(10);

        analyzerApps.getChildren().addAll(
                analyzerIconView,
                analyzerLabel
        );

        analyzerApps.setAlignment(Pos.CENTER_LEFT);
        desktopIcons.getChildren().add(analyzerApps);

        analyzerApps.setOnMouseClicked(actionEvent -> {

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

            Image[] articles = {
                    new Image("file:src/images/news1.png"),
                    new Image("file:src/images/news2.png"),
                    new Image("file:src/images/news3.png"),
                    new Image("file:src/images/news4.png"),
                     new Image("file:src/images/news5.png")


           };

            ImageView newsView = new ImageView(articles[0]);


            newsView.setFitWidth(450);
            newsView.setPreserveRatio(true);
            Image arrowImage = new Image("file:src/images/arrow.png");

            ImageView arrowView = new ImageView(arrowImage);
            arrowView.setFitWidth(35);
            arrowView.setPreserveRatio(true);

            Button nextButton = new Button();
            nextButton.setGraphic(arrowView);

            StackPane newsPane = new StackPane();
            newsPane.getChildren().add(newsView);
            newsPane.getChildren().add(nextButton);
            StackPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);
            StackPane.setMargin(nextButton, new Insets(0, 410, 35, 40));

            int[] currentArticle = {0};

            nextButton.setOnAction(e -> {


                if (currentArticle[0] < articles.length) {
                    currentArticle[0]++;
                    newsView.setImage(articles[currentArticle[0]]);
                }
            });
            amayaLayout.getChildren().add(newsPane);


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
                musicPlayer.pause();

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


                Button closeButt = new Button("X");

            StackPane videoPane = new StackPane();
            videoPane.setMaxSize(680, 425);
            videoPane.setPrefSize(680, 425);
            videoPane.getChildren().addAll(videoView, closeButt);
            StackPane.setAlignment(closeButt, Pos.TOP_RIGHT);

            amayaLayout.getChildren().add(videoPane);

            cyberFemplayer.play();

            closeButt.setOnAction(e -> {
                cyberFemplayer.stop();
                amayaLayout.getChildren().remove(videoPane);
                musicPlayer.play();

        });
        });
        Image womanIconImage =
                new Image("file:src/images/woman_icon.png");

        ImageView womanIconView =
                new ImageView(womanIconImage);

        womanIconView.setFitWidth(60);
        womanIconView.setPreserveRatio(true);

        Label womanLabel = new Label("womanosphere");

        womanLabel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;"
        );

        VBox womanIconBox = new VBox(10);
        womanIconBox.setAlignment(Pos.CENTER_LEFT);

        womanIconBox.getChildren().addAll(
                womanIconView,
                womanLabel
        );

        desktopIcons.getChildren().add(womanIconBox);
        womanIconBox.setOnMouseClicked(actionEvent1 -> {

            Image womansphereImage = new Image("file:src/images/home.png");
            ImageView womanSphereview = new ImageView(womansphereImage);
            womanSphereview.setFitWidth(610);
            womanSphereview.setPreserveRatio(true);

            Pane womanSpherelayout = new Pane();
            womanSpherelayout.getChildren().add(womanSphereview);

            Button home = new Button("home");
            home.setStyle("-fx-background-color: lightgray;");
            Button exit =  new Button("X");

            home.setLayoutX(20);
            home.setLayoutY(10);





            Button prayerPost = new Button("womanosphere");
            prayerPost.setStyle("-fx-background-color: lightgray;");
            prayerPost.setLayoutX(100);
            prayerPost.setLayoutY(10);



            Button herBlog = new Button("blog");
            herBlog.setStyle("-fx-background-color: lightgray;");
            herBlog.setLayoutX(230);
            herBlog.setLayoutY(10);


            exit.setLayoutX(570);
            exit.setLayoutY(15);
            exit.setPrefSize(28, 28);

            exit.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black;"
            );


            Image prayerPostimage = new Image("file:src/images/blog.png");
            Image blogImage = new Image("file:src/images/manosphere.png");

            home.setOnAction(event -> {
                womanSphereview.setImage(womansphereImage);
            });

            prayerPost.setOnAction(event -> {
                womanSphereview.setImage(prayerPostimage);
            });

            herBlog.setOnAction(event -> {
                womanSphereview.setImage(blogImage);
            });



            womanSpherelayout.getChildren().addAll(
                    home,
                    prayerPost,
                    herBlog,

                    exit
            );


            ScrollPane scrollPane = new ScrollPane(womanSpherelayout);

            scrollPane.setPrefSize(610, 550);
            scrollPane.setMaxSize(610, 550);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

            exit.setOnAction(event -> {
                amayaLayout.getChildren().remove(scrollPane);
            });

            amayaLayout.getChildren().add(scrollPane);



        });

        Image passwordIconImage =
                new Image("file:src/images/password_icon.png");

        ImageView passwordIconView =
                new ImageView(passwordIconImage);

        passwordIconView.setFitWidth(60);
        passwordIconView.setPreserveRatio(true);

        Label passwordLabel = new Label("???");

        passwordLabel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;"
        );

        VBox passwordIconBox = new VBox(10);
        passwordIconBox.setAlignment(Pos.CENTER_LEFT);

        passwordIconBox.getChildren().addAll(
                passwordIconView,
                passwordLabel
        );

        desktopIcons.getChildren().add(passwordIconBox);

passwordIconBox.setOnMouseClicked(event -> {

    PasswordField passwordBox = new PasswordField();
    passwordBox.setPromptText("enter password. hint: explore prayer's desktop, find capped letters.");
    passwordBox.setMaxWidth(200);

    Button enterButton = new Button("ENTER");

    VBox passwordStuff = new VBox(10, passwordBox, enterButton);
    passwordStuff.setAlignment(Pos.CENTER);

    StackPane passwordScreen = new StackPane(passwordStuff);
    passwordScreen.setStyle("-fx-background-color: black;");

    amayaLayout.getChildren().add(passwordScreen);

    enterButton.setOnAction(e -> {

        if (passwordBox.getText().equals("WOMANOSPHERE")) {

            musicPlayer.stop();

            amayaLayout.getChildren().clear();

            Media psa = new Media(
                    new java.io.File("src/videos/psa.mp4")
                            .toURI()
                            .toString()
            );
            MediaPlayer psaPlayer = new MediaPlayer(psa);
            MediaView psaView = new MediaView(psaPlayer);

            psaView.fitWidthProperty().bind(amayaLayout.widthProperty());
            psaView.fitHeightProperty().bind(amayaLayout.heightProperty());
            psaView.setPreserveRatio(true);

            amayaLayout.getChildren().add(psaView);

            psaPlayer.play();

            psaPlayer.setOnEndOfMedia(() -> {
                primaryStage.close();
            });
        }
    });
});



    }

    public static void main(String[] args) {
        launch(args);
    }
}