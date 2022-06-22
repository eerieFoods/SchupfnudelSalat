module com.github.eeriefoods.snsclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires java.desktop;
    requires org.jetbrains.annotations;

    exports com.github.eeriefoods.snsclient;
    exports com.github.eeriefoods.snsclient.controller;
    exports com.github.eeriefoods.snsclient.model;

    opens com.github.eeriefoods.snsclient.controller to javafx.fxml;
    opens com.github.eeriefoods.snsclient.model to com.google.gson, javafx.base;
}