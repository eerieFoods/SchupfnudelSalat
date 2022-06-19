package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ToolBarController {
    @FXML public AnchorPane toolBar;
    @FXML private Button BTNCreate;
    @FXML private Button BTNDelete;
    @FXML private TextField TFDSearch;
    @FXML private Button BTNSearch;
    private MainController mainController;
    private StudentTableController studentTableController;
    private TableView<Student> studentTableView;


    public void injectMainController(MainController mainController){
        this.mainController = mainController;
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = studentTableController.TCS_View;
    }
    @FXML private void initialize(){

        initButtonFunctions();
    }

    private void initButtonFunctions(){

        TFDSearch.setOnAction(event -> {
            studentSearch();
        });

        BTNSearch.setOnAction(event -> {
            studentSearch();
        });

        BTNCreate.setOnAction(event -> {
            mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
        });
        BTNDelete.setOnAction(event -> {
            Student student = studentTableView.getSelectionModel().getSelectedItem();
            studentTableController.deleteStudent(student);
            studentTableView.getItems().remove(student);
        });

    }

    private void studentSearch() {
        try {
            FilteredList<Student> filteredData = new FilteredList<>(FXCollections.observableList(StudentService.getAllStudents()));
            studentTableView.setItems(filteredData);
            filteredData.setPredicate(studentTableController.createPredicate(TFDSearch.getText()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
