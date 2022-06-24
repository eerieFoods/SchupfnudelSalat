package com.github.eeriefoods.snsclient.shared;

import com.github.eeriefoods.snsclient.SnsClientApplication;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class NotificationHandler {

    /**
     * Logs errors but shows no Dialog Window
     * @param errorResponse StackTraceElement @NotNull []
     */
    public static void logNoNotification(StackTraceElement @NotNull [] errorResponse) {
        Arrays.stream(errorResponse)
                .map(er -> "\tat" + er)
                .forEach(System.err::println);
    }

    /**
     * Logs errors but shows no Dialog Window
     * @param errorResponse String
     */
    public static void logNoNotification(String errorResponse) {
        System.err.println(errorResponse);
    }

    /**
     * Logs Exception errors AND shows a Dialog Window
     * @param exception The Exception to handle
     */
    public static void handleExceptionError(Exception exception) {
        exception.printStackTrace();
        showErrorNotification("Oh Nein!", "Oh Nein. Irgendetwas ist schief gelaufen!", "Die Software wird beendet!", true);
    }
    /**
     * Logs HTTP errors AND shows a Dialog Window
     * @param errorResponse StackTraceElement @NotNull []
     */
    public static void handleHttpError(HttpResponse<String> errorResponse) {
        System.out.println(errorResponse);
        showErrorNotification("Oh Nein!", "Oh Nein! Etwas ist bei der Verbindung mit dem Backend schief gelaufen!", false);
    }

    /**
     * Dialog Window for displaying a user Notification (e.g. Student successfully saved)
     * @param title Title to show
     * @param header Content to show
     */
    public static void showUserNotification(String title, String header) {
        showUserNotification(title, header, null);
    }

    /**
     * Dialog Window for displaying a user Notification (e.g. Student successfully saved)
     * @param title Title tho show
     * @param header Upper content to show
     * @param content Lower content to show
     */
    public static void showUserNotification(String title, String header, String content) {
        Alert userNotification = new Alert(Alert.AlertType.INFORMATION);
        Stage notificationStage = (Stage) userNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(getDHBWRaute());
        userNotification.setTitle(title);
        userNotification.setHeaderText(header);
        userNotification.setContentText(content);
        userNotification.showAndWait();
    }

    /**
     * Dialog Window for displaying a Warning Notification (e.g. Student successfully saved)
     * @param title Title to show
     * @param text Content to show
     */
    public static void showWarningNotification(String title, String text) {
        showWarningNotification(title, text, null);
    }

    public static void showWarningNotification(String title, String text, String content) {
        Alert warningNotification = new Alert(Alert.AlertType.WARNING);
        Stage notificationStage = (Stage) warningNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(getDHBWRaute());
        warningNotification.setTitle(title);
        warningNotification.setHeaderText(text);
        warningNotification.setContentText(content);
        warningNotification.showAndWait();
    }

    /**
     * Dialog Window for displaying an Error user Notification (e.g. Program has crashed)
     * @param title The title to show
     * @param text The content to show
     * @param endProgram Whether the program should exit or not afterwards
     */
    public static void showErrorNotification(String title, String text, boolean endProgram) {
        showErrorNotification(title, text, null, endProgram);
    }

    /**
     * Dialog Window for displaying an Error user Notification (e.g. Program has crashed)
     * @param title The title to show
     * @param text The upper content to show
     * @param content The lower content to show
     * @param endProgram Whether the program should exit or not afterwards
     */
    public static void showErrorNotification(String title, String text, String content, boolean endProgram) {
        Alert errorNotification = new Alert(Alert.AlertType.ERROR);
        Stage notificationStage = (Stage) errorNotification.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(getDHBWRaute());
        errorNotification.setTitle(title);
        errorNotification.setHeaderText(text);
        errorNotification.setContentText(content);
        errorNotification.showAndWait();
        if (endProgram) {
            System.exit(0);
        }
    }

    /**
     * Dialog Window for getting User Confirmation on an Action.
     * @param title The title to show
     * @param text The content to show
     * @return true: confirmation has been given. Commence with action.
     *         <p>false: confirmation HASN'T been given. Abort action! </p>
     */
    public boolean askForConfirmation(String title, String text) {
        return askForConfirmation(title, text, null);
    }

    /**
     * Dialog Window for getting User Confirmation on an Action.
     * @param title The title to show
     * @param text The upper content to show
     * @param content The lower content to show
     * @return true: confirmation has been given. Commence with action.
     *         <p>false: confirmation HASN'T been given. Abort action! </p>
     */
    public boolean askForConfirmation(String title, String text, String content) {
        boolean confirmation = false;
        Alert askForConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        Stage notificationStage = (Stage) askForConfirmation.getDialogPane().getScene().getWindow();
        notificationStage.getIcons().add(getDHBWRaute());
        askForConfirmation.setTitle(title);
        askForConfirmation.setHeaderText(text);
        askForConfirmation.setContentText(content);
        Optional<ButtonType> result = askForConfirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            confirmation = true;
        }
        return confirmation;
    }

    private static Image getDHBWRaute() {
        return new Image(Objects.requireNonNull(SnsClientApplication.class.getResourceAsStream("assets/images/DHBWRaute.png")));

    }
}
