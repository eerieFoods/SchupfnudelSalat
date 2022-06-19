package com.github.eeriefoods.snsclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML public StudentToolBarController studentToolBarController;
    @FXML public StudentAddBarController studentAddBarController;
    @FXML public StudentTableController studentTableController;
    @FXML public CourseToolBarController courseToolBarController;
    @FXML public CourseAddBarController courseAddBarController;
    @FXML public CourseTableController courseTableController;
    @FXML public TabPane tabPane;
    @FXML private AnchorPane studentToolBar;
    @FXML private AnchorPane studentAddBar;
    @FXML private AnchorPane courseToolBar;
    @FXML private AnchorPane courseAddBar;

    @FXML private void initialize(){
        studentToolBarController.injectMainController(this);
        studentAddBarController.injectMainController(this);
        courseTableController.injectMainController(this);
        courseToolBarController.injectMainController(this);
        courseAddBarController.injectMainController(this);
    }

    public void switchBar(Tab tab){
        switch (tab.getId()){
            case "Student:innen":
                if(studentToolBar.isVisible()){
                    studentAddBar.setVisible(true);
                    studentToolBar.setVisible(false);
                }else {
                    studentAddBar.setVisible(false);
                    studentToolBar.setVisible(true);
                }
                break;
            case "Kurse":
                if(courseToolBar.isVisible()){
                    courseAddBar.setVisible(true);
                    courseToolBar.setVisible(false);
                }else {
                    courseAddBar.setVisible(false);
                    courseToolBar.setVisible(true);
                }
                break;
            default:
                System.out.println("TabPane error");
                break;
        }
    }

}