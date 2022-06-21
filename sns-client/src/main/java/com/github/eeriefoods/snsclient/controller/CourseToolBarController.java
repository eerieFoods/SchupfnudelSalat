package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;

public class CourseToolBarController extends ToolBar{
    @Override
    public void initButtonFunctions(){

        TFDSearch.setOnKeyTyped(event -> courseSearch());

        BTNCreate.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));

        BTNDelete.setOnAction(event -> {
            NotificationHandler nh = new NotificationHandler();
            if (nh.askForConfirmation("Wirklich löschen?", "Im Kurs " + courseTableView.getSelectionModel().getSelectedItem().getId() + " befinden sich noch " + " Student:innen. Soll der Kurs wirklich gelöscht werden?") == true) {
                Course course = courseTableView.getSelectionModel().getSelectedItem();
                courseTableController.deleteCourse(course);
                courseTableView.getItems().remove(course);
        }});

    }

}
