package com.github.eeriefoods.snsclient;

import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;
import javafx.event.Event;
import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.Student;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;
import com.github.eeriefoods.snsclient.model.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainController {

    @FXML private TableView<Student> TCS_View;
    @FXML private TableColumn<Student, Integer> TCS_ID;
    @FXML private TableColumn<Student, String> TCS_FirstName;
    @FXML private TableColumn<Student, String> TCS_LastName;
    @FXML private TableColumn<Student, String> TCS_Course;
    @FXML private TableColumn<Student, String> TCS_Company;
    @FXML private TableColumn<Student, JavaLevel> TCS_JavaLevel;

    @FXML private TableView<Course> TCK_View;
    @FXML private TableColumn<Course, Integer> TCK_ID;
    @FXML private TableColumn<Course, String> TCK_FriendlyName;
    @FXML private TableColumn<Course, String> TCK_Room;

    @FXML private void initialize() {
        
        TCS_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        TCS_FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TCS_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TCS_LastName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_JavaLevel.setCellValueFactory(new PropertyValueFactory<>("javaLevel"));
//        TCS_JavaLevel.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_Company.setCellValueFactory(new PropertyValueFactory<>("company"));
        TCS_Company.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_Course.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        TCS_Course.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_View.getItems().setAll(parseStudentList());
        TCS_View.setRowFactory(
                new Callback<TableView<Student>, TableRow<Student>>() {
                    @Override
                    public TableRow<Student> call(TableView<Student> tableView) {
                        final TableRow<Student> row = new TableRow<>();
                        final ContextMenu rowMenu = new ContextMenu();
                        MenuItem addItem = new MenuItem("Add");
                        addItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                CTXAdd();
                            }
                        });
                        MenuItem editItem = new MenuItem("Edit");
                        editItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                CTXEdit(String.valueOf(row.getItem().getStudentId()));
                            }
                        });
                        MenuItem removeItem = new MenuItem("Delete");
                        removeItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                CTXDelete(String.valueOf(row.getItem().getStudentId()));
                            }
                        });
                        rowMenu.getItems().addAll(editItem, removeItem);

                        // only display context menu for non-null items:
                        row.contextMenuProperty().bind(
                                Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                        .then(rowMenu)
                                        .otherwise((ContextMenu)null));
                        return row;
                    }
                });


        TCK_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TCK_FriendlyName.setCellValueFactory(new PropertyValueFactory<>("friendlyName"));
        TCK_Room.setCellValueFactory(new PropertyValueFactory<>("room"));
        TCK_View.getItems().setAll(parseCourseList());

        TCS_FirstName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(event.getNewValue());
            updateStudent(event.getRowValue());});
        TCS_LastName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
            updateStudent(event.getRowValue());});
        TCS_Company.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCompany(event.getNewValue());
            updateStudent(event.getRowValue());});
        TCS_Course.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCourse(event.getNewValue());
            updateStudent(event.getRowValue());});

        TCS_View.setEditable(true);
    }

    private void updateStudent(Student student){
            try {
                StudentService.updateStudent(student);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
    }

    private List<Course> parseCourseList() {
        try {
            return CourseService.getCourses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }    }

    private List<Student> parseStudentList() {
        try {
            return StudentService.getAllStudents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void editableClos(){
        TCS_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    public void CTXAdd() {
        //Add Button was clicked. Do shit here!
        showMessageDialog(null, "In Theory, we could create a new Student here. \n We don't tho.");
    }

    public void CTXEdit(String studentId) {
        //Add Button was clicked. Do shit here!
        showMessageDialog(null, "In Theory, we could edit the Student with the ID: " + studentId + " here. \n We don't tho.");
    }

    public void CTXDelete(String studentId) {
        //Add Button was clicked. Do shit here!
        showMessageDialog(null, "In Theory, we could delete the Student with the ID: " + studentId + " here. \n We don't tho.");
    }
}