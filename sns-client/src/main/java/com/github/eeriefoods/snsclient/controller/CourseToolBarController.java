package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;

public class CourseToolBarController extends ToolBar {
    @Override
    public void initButtonFunctions() {

        TFDSearch.setOnKeyTyped(event -> courseSearch());

        BTNCreate.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));

        BTNDelete.setOnAction(event -> {
            if (courseTableView.getSelectionModel().getSelectedItem().getMemberCount() != 0) {
                NotificationHandler.showWarningNotification("Löschen nicht möglich", "Der Kurs " + courseTableView.getSelectionModel().getSelectedItem().getId() + " kann nicht gelöscht werden, weil er noch Student:innen enthält.", "Lösche bitte zunächst alle Student:innen oder weise Sie anderen Kursen zu!");
            } else {
                NotificationHandler nh = new NotificationHandler();
                if (nh.askForConfirmation("Wirklich löschen?", "Im Kurs " + courseTableView.getSelectionModel().getSelectedItem().getId() + " befinden sich noch" + " Student:innen. Soll der Kurs wirklich gelöscht werden?")) {
                    Course course = courseTableView.getSelectionModel().getSelectedItem();
                    courseTableController.deleteCourse(course);
                    courseTableView.getItems().remove(course);
                }
            }});


    }

    @Override
    public void updateFilteredList() {
        filteredCourseData = new FilteredList<>(FXCollections.observableList(CourseService.getCourses()));
    }
}
