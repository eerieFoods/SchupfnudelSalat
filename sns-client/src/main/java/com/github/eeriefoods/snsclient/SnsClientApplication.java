package com.github.eeriefoods.snsclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SnsClientApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnsClientApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 820);
        stage.setTitle("DHBW Student Management Tool");
        stage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        stage.setMinWidth(1300);
        stage.setMinHeight(820);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}