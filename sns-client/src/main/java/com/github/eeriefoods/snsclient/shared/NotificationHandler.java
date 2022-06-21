package com.github.eeriefoods.snsclient.shared;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.http.HttpResponse;
import java.util.Optional;

public class NotificationHandler {

    public static void handleError(HttpResponse<String> errorResponse) {

    }

    //Dialog Window for displaying a user Notification (eg. Student successfully saved)
    //Usage: showUserNotification("VERY IMPORTANT TITLE", "VERY IMPORTANT MESSAGE")
    //Returns: nothing

    public static void showUserNotification(String title, String text) {
        Alert userNotification = new Alert(Alert.AlertType.INFORMATION);
        userNotification.setTitle(title);
        userNotification.setHeaderText(text);
        userNotification.showAndWait();
    }

    //Dialog Window for displaying an Error user Notification (eg. Program has crashed)
    //Usage: showErrorNotification("OhNoNoNo", "Program has crashed. Too bad.")
    //Returns: nothing

    public static void showErrorNotification(String title, String text) {
        Alert errorNotification = new Alert(Alert.AlertType.ERROR);
        errorNotification.setTitle(title);
        errorNotification.setHeaderText(text);
        errorNotification.showAndWait();
    }

    //Dialog Window for displaying a Warning Notification (eg. Student successfully saved)
    //Usage: showUserNotification("Noot Noot", "The Program might crash. Shit happens.")
    //Returns: nothing

    public static void showWarningNotification(String title, String text) {
        Alert warningNotification = new Alert(Alert.AlertType.WARNING);
        warningNotification.setTitle(title);
        warningNotification.setHeaderText(text);
        warningNotification.showAndWait();
    }

    //Dialog Window for getting User Confirmation on an Action.
    //Usage: askForConfirmation("Do you REALLY want to do this???", "ARE YOU REALLY SURE, YOU WANT TO DELETE YOUR HARD DRIVE?")
    //Returns: response with values true, false
    //                              true: confirmation has been given. Commence with action.
    //                              false: confirmation HASN'T been given. Abort action!

    public boolean askForConfirmation(String title, String text) {
        boolean confirmation = false;
        Alert askForConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        askForConfirmation.setTitle(title);
        askForConfirmation.setHeaderText(text);
        Optional<ButtonType> result = askForConfirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL){
            confirmation = false;
        }

        if (result.isPresent() && result.get() == ButtonType.OK){
            confirmation = true;
        }
        return confirmation;
    }
}
