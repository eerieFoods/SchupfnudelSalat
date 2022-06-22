package com.github.eeriefoods.snsclient.shared;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpResponse;
import java.util.Optional;

public class NotificationHandler {


    public static void handleExceptionError(StackTraceElement @NotNull [] errorResponse) {
        for (int i = 1; i < errorResponse.length; i++)
            System.err.println("\tat " + errorResponse[i]);
        showErrorNotification("Oh No!", "it seems like the connection to the backend has gone wrong. The Student Manager Software will close now!.", true);
    }

    public static void handleHttpError(HttpResponse<String> errorResponse) {
        System.out.println(errorResponse);
        showErrorNotification("Oh No!", "it seems like the connection to the backend has gone wrong. The Student Manager Software will close now!", true);
    }

    //Dialog Window for displaying a user Notification (eg. Student successfully saved)
    //Usage: showUserNotification("VERY IMPORTANT TITLE", "VERY IMPORTANT MESSAGE")
    //Returns: nothing

    public static void showUserNotification(String title, String header) {
        Alert userNotification = new Alert(Alert.AlertType.INFORMATION);
        Stage notificationStage = (Stage) userNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        userNotification.setTitle(title);
        userNotification.setHeaderText(header);
        userNotification.showAndWait();
    }

    public static void showUserNotification(String title, String header, String content) {
        Alert userNotification = new Alert(Alert.AlertType.INFORMATION);
        Stage notificationStage = (Stage) userNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        userNotification.setTitle(title);
        userNotification.setHeaderText(header);
        userNotification.setContentText(content);
        userNotification.showAndWait();
    }

    //Dialog Window for displaying a Warning Notification (eg. Student successfully saved)
    //Usage: showUserNotification("Noot Noot", "The Program might crash. Shit happens.")
    //Returns: nothing

    public static void showWarningNotification(String title, String text) {
        Alert warningNotification = new Alert(Alert.AlertType.WARNING);
        Stage notificationStage = (Stage) warningNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        warningNotification.setTitle(title);
        warningNotification.setHeaderText(text);
        warningNotification.showAndWait();
    }

    public static void showWarningNotification(String title, String text, String content) {
        Alert warningNotification = new Alert(Alert.AlertType.WARNING);
        Stage notificationStage = (Stage) warningNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        warningNotification.setTitle(title);
        warningNotification.setHeaderText(text);
        warningNotification.setContentText(content);
        warningNotification.showAndWait();
    }

    //Dialog Window for displaying an Error user Notification (eg. Program has crashed)
    //Usage: showErrorNotification("OhNoNoNo", "Program has crashed. Too bad.")
    //Returns: nothing

    public static void showErrorNotification(String title, String text, boolean endProgram) {
        Alert errorNotification = new Alert(Alert.AlertType.ERROR);
        Stage notificationStage = (Stage) errorNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        errorNotification.setTitle(title);
        errorNotification.setHeaderText(text);
        errorNotification.showAndWait();
        if (endProgram) {
            System.exit(0);
        }
    }

    public static void showErrorNotification(String title, String text, String content, boolean endProgram) {
        Alert errorNotification = new Alert(Alert.AlertType.ERROR);
        Stage notificationStage = (Stage) errorNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        errorNotification.setTitle(title);
        errorNotification.setHeaderText(text);
        errorNotification.setContentText(content);
        errorNotification.showAndWait();
        if (endProgram) {
            System.exit(0);
        }
    }


    //Dialog Window for getting User Confirmation on an Action.
    //Usage: askForConfirmation("Do you REALLY want to do this???", "ARE YOU REALLY SURE, YOU WANT TO DELETE YOUR HARD DRIVE?")
    //Returns: response with values true, false
    //                              true: confirmation has been given. Commence with action.
    //                              false: confirmation HASN'T been given. Abort action!

    public boolean askForConfirmation(String title, String text) {
        boolean confirmation = false;
        Alert askForConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        Stage notificationStage = (Stage) askForConfirmation.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        askForConfirmation.setTitle(title);
        askForConfirmation.setHeaderText(text);
        Optional<ButtonType> result = askForConfirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            confirmation = true;
        }
        return confirmation;
    }

    public boolean askForConfirmation(String title, String text, String content) {
        boolean confirmation = false;
        Alert askForConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        Stage notificationStage = (Stage) askForConfirmation.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(new Image("file:src/main/resources/com/github/eeriefoods/snsclient/assets/images/DHBWRaute.png"));
        askForConfirmation.setTitle(title);
        askForConfirmation.setHeaderText(text);
        askForConfirmation.setContentText(content);
        Optional<ButtonType> result = askForConfirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            confirmation = true;
        }
        return confirmation;
    }
}
