package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Student;

public class StudentToolBarController extends ToolBar {
    @Override
    public void initButtonFunctions(){

        TFDSearch.setOnKeyTyped(event -> studentSearch());

        BTNCreate.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));

        BTNDelete.setOnAction(event -> {
            Student student = studentTableView.getSelectionModel().getSelectedItem();
            studentTableController.deleteStudent(student);
            studentTableView.getItems().remove(student);
        });

    }
}
