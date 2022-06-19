package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;

public class CourseToolBarController extends ToolBar{
    @Override
    public void initButtonFunctions(){

        TFDSearch.setOnAction(event -> courseSearch());

        BTNSearch.setOnAction(event ->courseSearch());

        BTNCreate.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));
        BTNDelete.setOnAction(event -> {
            Course course = courseTableView.getSelectionModel().getSelectedItem();
            courseTableController.deleteCourse(course);
            courseTableView.getItems().remove(course);
        });

    }

}
