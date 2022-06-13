package com.github.eeriefoods.snsclient.model;

import java.util.Objects;

public class Student {

    private Integer studentId;
    private String firstName;
    private String lastName;
    private JavaLevel javaLevel;
    private String company;
    private String courseId;

    /*
        Boilerplate Code, weil Lombok nicht i.V.m JavaFX & GSON funktioniert.
        Bekanntes Problem seit JDK9+
     */

    public Student(Integer studentId, String firstName, String lastName, JavaLevel javaLevel, String company, String courseId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.javaLevel = javaLevel;
        this.company = company;
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public JavaLevel getJavaLevel() {
        return javaLevel;
    }

    public void setJavaLevel(JavaLevel javaLevel) {
        this.javaLevel = javaLevel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourse(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && javaLevel == student.javaLevel && Objects.equals(company, student.company) && Objects.equals(courseId, student.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, javaLevel, company, courseId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", javaLevel=" + javaLevel +
                ", company='" + company + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
