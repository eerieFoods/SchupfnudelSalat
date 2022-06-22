package com.github.eeriefoods.snsclient.service;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.shared.HttpFactory;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static com.github.eeriefoods.snsclient.shared.Constants.getServerUri;
import static com.github.eeriefoods.snsclient.shared.NotificationHandler.handleExceptionError;

public class CourseService {

    public static final String ENDPOINT = "course";
    private static final Gson gson;

    static {
        gson = new Gson();
    }

    public static List<Course> getCourses() {
        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendGetRequest(getServerUri(ENDPOINT));
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }

        assert response != null;
        return gson.fromJson(response.body(), new TypeToken<List<Course>>() {}.getType());
    }

    public static Course createCourse(Course course) {
        String requestBody = gson.toJson(course);

        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendPostJsonRequest(getServerUri(ENDPOINT), requestBody);
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }

        assert response != null;
        if (response.statusCode() == 409) {
            NotificationHandler
                    .showWarningNotification("Kurs existiert bereits", "Ein Kurs mit der ID %s existiert bereits".formatted(course.getId()));
        }

        return gson.fromJson(response.body(), Course.class);
    }

    public static Course updateCourse(Course course) {
        String requestBody = gson.toJson(course);

        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendPutJsonRequest(getServerUri(ENDPOINT), requestBody);
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }

        assert response != null;
        return gson.fromJson(response.body(), Course.class);
    }

    public static void deleteCourse(String courseId) {
        try {
            HttpFactory.sendDeleteRequest(getServerUri("%s/%s".formatted(ENDPOINT, courseId)));
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }
    }

    public static Course getCourse(String courseId) {
        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendGetRequest(getServerUri("%s/%s".formatted(ENDPOINT, courseId)));
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }

        assert response != null;
        return gson.fromJson(response.body(), Course.class);
    }

    public static Course addMemberToCourse(String courseId, Student student) {
        String requestBody = gson.toJson(student);
        System.out.println("lol");
        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendPutJsonRequest(getServerUri("%s/%s/add".formatted(ENDPOINT, courseId)), requestBody);
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }

        assert response != null;
        return gson.fromJson(response.body(), Course.class);
    }

    public static void removeMemberFromCourse(String courseId, Student student) {
        String requestBody = gson.toJson(student);
        try {
            HttpFactory.sendPutJsonRequest(getServerUri("%s/%s/remove".formatted(ENDPOINT, courseId)), requestBody);
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }
    }

}
