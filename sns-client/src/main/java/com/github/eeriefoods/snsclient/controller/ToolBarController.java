package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ToolBarController  {
    @FXML public AnchorPane toolBar;
    @FXML private Button BTNCreate;
    @FXML private Button BTNDelete;
    @FXML private Button BTNEdit;
    @FXML private TextField TFDSearch;
    @FXML private Button BTNSearch;
    private MainController mainController;

    @FXML private void initialize(){

        BTNCreate.setOnAction(event -> {
//            initAdd();
            });

        BTNDelete.setOnAction(event -> {
            System.out.println(mainController.tabPane.getSelectionModel().getSelectedItem());
        });
    }

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

}
