module com.github.eeriefoods.snsclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    opens com.github.eeriefoods.snsclient to javafx.fxml;
    opens com.github.eeriefoods.snsclient.model to com.google.gson;
    exports com.github.eeriefoods.snsclient;
}