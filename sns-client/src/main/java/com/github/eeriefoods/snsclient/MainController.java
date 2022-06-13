package com.github.eeriefoods.snsclient;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.random.*;
import java.util.zip.ZipEntry;

import com.github.eeriefoods.snsclient.model.*;
import javafx.scene.control.TreeTableView;

public class MainController {

    @FXML
    private Label lable_test;

    @FXML
    private Button Test;
    @FXML private TreeTableView<Student> TESTBaumTable;
    @FXML private TreeTableColumn<Student, Integer> TTCStudentId;
    @FXML private TreeTableColumn<Student, String> TTCFirstName;
    @FXML private TreeTableColumn<Student, String> TTCLastName;
    @FXML private TreeTableColumn<Student, String> TTCCompany;
    @FXML private TreeTableColumn<Student, String> TTCCourses;
    @FXML private TreeTableColumn<Student, JavaLevel> TTCJavaLevel;

    @FXML private TableView<Student> TTS_View;
    @FXML private TableColumn<Student, Integer> TTS_ID;
    @FXML private TableColumn<Student, String> TTS_FirstName;
    @FXML private TableColumn<Student, String> TTS_LastName;
    @FXML private TableColumn<Student, String> TTS_Course;
    @FXML private TableColumn<Student, String> TTS_Company;
    @FXML private TableColumn<Student, JavaLevel> TTS_JavaLevel;


    @FXML
    protected void onTest() {
        lable_test.setText("Welcome to JavaFX Application!");
        Test.setText("lol");

        for (int i = 0; i < 10; i++) {



        }


    }

    @FXML private void initialize() {

        Course Test = new Course("TINF21AI2", "123B");
        //TTCStudentId.setCellFactory(TextFieldTreeTableCell.<Student>forTreeTableColumn());

        TTCStudentId.setCellValueFactory((CellDataFeatures<Student, Integer> p) ->
            new ReadOnlyIntegerWrapper(p.getValue().getValue().getStudentId()).asObject());

//        TTCStudentId.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

        TTCCompany.setCellValueFactory((CellDataFeatures<Student, String> p) ->
           new ReadOnlyStringWrapper(p.getValue().getValue().getCompany()));

        TESTBaumTable.setOnMouseClicked(event -> {
            if (event.getClickCount() % 2 == 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Heyyyy");
                a.setContentText(":)");
                a.show();
            }
        });

        TreeItem<Course> courses = new TreeItem<>(new Course("TINF21AI2", "123B"));


        for (int i = 0; i < 10; i++) {

            TreeItem<Student> student = new TreeItem<Student>(new Student(i, "ASDF", "WASD", JavaLevel.SEHR_GUT, i + " NONAMECOMPANY", Test));
            courses.getChildren().add(student);


         }
        courses.setExpanded(true);
        TESTBaumTable.setRoot(courses);



        TTS_ID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        //TTS_View.getItems().setAll(parseStudentList());
    }

    private List<Student> parseStudentList() {
        Course Test = new Course("TINF21AI2", "123B");
        List<Student> Students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student iStudent = new Student(i, "ASDF", "WASD", JavaLevel.SEHR_GUT, i + " NONAMECOMPANY", Test);
            Students.add(iStudent);
        }
        return Students;
    }
}