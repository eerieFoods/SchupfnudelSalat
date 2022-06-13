package com.github.eeriefoods.snsclient.service;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.shared.HttpFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static com.github.eeriefoods.snsclient.shared.Constants.getServerUri;

public class CourseService {

    // TODO: ErrorHandling

    public static final String ENDPOINT = "course";
    private static final Gson gson;

    static {
        gson = new Gson();
    }

    public static List<Course> getCourses() throws IOException, InterruptedException {
        HttpResponse<String> response = HttpFactory.sendGetRequest(getServerUri(ENDPOINT));

        return gson.fromJson(response.body(), new TypeToken<List<Course>>() {}.getType());
    }

    public static Course createCourse(Course course) throws IOException, InterruptedException {
        String requestBody = gson.toJson(course);

        HttpResponse<String> response = HttpFactory.sendPostJsonRequest(getServerUri(ENDPOINT), requestBody);

        return gson.fromJson(response.body(), Course.class);
    }

    public static Course updateCourse(Course course) throws IOException, InterruptedException {
        String requestBody = gson.toJson(course);

        HttpResponse<String> response = HttpFactory.sendPutJsonRequest(getServerUri(ENDPOINT), requestBody);

        return gson.fromJson(response.body(), Course.class);
    }

    public static void deleteCourse(String courseId) throws IOException, InterruptedException {
        HttpFactory.sendDeleteRequest(getServerUri("%s/%s".formatted(ENDPOINT, courseId)));
    }

    public static Course getCourse(String courseId) throws IOException, InterruptedException {
        HttpResponse<String> response = HttpFactory.sendGetRequest(getServerUri("%s/%s".formatted(ENDPOINT, courseId)));

        return gson.fromJson(response.body(), Course.class);
    }

    public static Course addMemberToCourse(String courseId, Student student) throws IOException, InterruptedException {
        String requestBody = gson.toJson(student);
        
        HttpResponse<String> response = HttpFactory.sendPutJsonRequest(getServerUri("%s/%s/add".formatted(ENDPOINT, courseId)), requestBody);

        return gson.fromJson(response.body(), Course.class);
    }

    public static void removeMemberFromCourse(String courseId, Student student) throws IOException, InterruptedException {
        String requestBody = gson.toJson(student);

        HttpFactory.sendPutJsonRequest(getServerUri("%s/%s/remove".formatted(ENDPOINT, courseId)), requestBody);
    }

}
