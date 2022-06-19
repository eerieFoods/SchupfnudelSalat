package com.github.eeriefoods.snsclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML public ToolBarController studentToolBarController;
//    @FXML public ToolBarController courseToolBarController;
    @FXML public StudentAddBarController studentAddBarController;
    @FXML public StudentTableController studentTableController;
    @FXML public CourseTableController courseTableController;
    @FXML public TabPane tabPane;
    @FXML private AnchorPane studentToolBar;
    @FXML private AnchorPane studentAddBar;

    @FXML private void initialize(){
//        courseToolBarController.injectMainController(this);
        studentToolBarController.injectMainController(this);
        studentAddBarController.injectMainController(this);
        courseTableController.injectMainController(this);
    }

    public void switchBar(Tab tab){
        switch (tab.getId()){
            case "Student:innen":
                if(studentToolBar.isVisible() == true){
                    studentAddBar.setVisible(true);
                    studentToolBar.setVisible(false);
                }else {
                    studentAddBar.setVisible(false);
                    studentToolBar.setVisible(true);
                }
                break;
            case "Kurs":
                System.out.println("k");
                break;
            default:
                System.out.println("TabPane error");
                break;
        }
    }

}