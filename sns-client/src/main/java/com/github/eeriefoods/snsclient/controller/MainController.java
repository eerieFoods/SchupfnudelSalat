package com.github.eeriefoods.snsclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML public TabPane tabPane;

    @FXML private ToolBarController toolBarController;

    @FXML private void initialize(){
        toolBarController.injectMainController(this);
    }

}