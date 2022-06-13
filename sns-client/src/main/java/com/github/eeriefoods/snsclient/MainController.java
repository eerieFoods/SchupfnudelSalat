package com.github.eeriefoods.snsclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import java.util.random.*;
import com.github.eeriefoods.snsclient.model.*;
import javafx.scene.control.TreeTableView;

public class MainController {

    @FXML
    private Label lable_test;

    @FXML
    private Button Test;
    @FXML
    TreeTableView<Student> students = new TreeTableView<Student>();
    @FXML
    TreeTableColumn<Student, Integer> studentId = new TreeTableColumn<>("Student ID");
    @FXML
    TreeTableColumn<Student, String> firstName = new TreeTableColumn<>("Erster Name");
    @FXML
    TreeTableColumn<Student, String> lastName = new TreeTableColumn<>("Letzter Name");
    @FXML
    TreeTableColumn<Student, String> cOmPaNy = new TreeTableColumn<>("fIrMa");
    @FXML
    TreeTableColumn<Student, String> CoUrSeS = new TreeTableColumn<>("KuRsE");
    @FXML
    TreeTableColumn<Student, JavaLevel> javaLevel = new TreeTableColumn<>("JAVA Lvl");

    @FXML
    studentId.



    @FXML
    protected void onTest() {
        lable_test.setText("Welcome to JavaFX Application!");
        Test.setText("lol");

        for (int i = 0; i < 10; i++) {



        }


    }
}