package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;

import java.io.IOException;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;

public class StudentToolBarController extends ToolBar {
    @Override
    public void initButtonFunctions(){

        TFDSearch.setOnKeyTyped(event -> studentSearch());

        BTNCreate.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));

        BTNDelete.setOnAction(event -> {
            NotificationHandler nh = new NotificationHandler();

            if (nh.askForConfirmation("Wirklich löschen?", "Soll der Student/die Studentin " + studentTableView.getSelectionModel().getSelectedItem().getFirstName() + " " + studentTableView.getSelectionModel().getSelectedItem().getLastName() + " wirklich gelöscht werden?") == true) {
                Student student = studentTableView.getSelectionModel().getSelectedItem();
                studentTableController.deleteStudent(student);
                studentTableView.getItems().remove(student);
            }
        });
    }
    @Override
    public void updateFilteredList(){
        try {
            filteredStudentData = new FilteredList<>(FXCollections.observableList(StudentService.getAllStudents()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
