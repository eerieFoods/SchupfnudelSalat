package com.github.eeriefoods.snsclient.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ToolBarController {
    @FXML public AnchorPane toolBar;
    @FXML private Button BTNCreate;
    @FXML private Button BTNEdit;
    @FXML private Button BTNDelete;
    @FXML private TextField TFDSearch;
    @FXML private Button BTNSearch;

    @FXML public void initialize() throws IOException {

    }


}
