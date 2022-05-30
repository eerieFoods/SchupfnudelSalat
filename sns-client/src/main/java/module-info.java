module com.github.eeriefoods.snsclient {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.github.eeriefoods.snsclient to javafx.fxml;
    exports com.github.eeriefoods.snsclient;
}