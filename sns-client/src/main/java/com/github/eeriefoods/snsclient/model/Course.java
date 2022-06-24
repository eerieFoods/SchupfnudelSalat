package com.github.eeriefoods.snsclient.model;

import java.util.Collection;
import java.util.Objects;

public class Course {

    private String id;
    private Integer memberCount;
    private String room;
    private Collection<Student> members;

    /*
        Boilerplate Code, weil Lombok nicht i.V.m JavaFX & GSON funktioniert.
        Bekanntes Problem seit JDK9+
     */

    public Course(String id, Integer memberCount, String room, Collection<Student> members) {
        this.id = id;
        this.memberCount = memberCount;
        this.room = room;
        this.members = members;
    }

    public Course(String id, Integer memberCount, String room) {
        this(id, memberCount, room, null);
    }

    public Course(String id, String room) {
    this(id, 0,room);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Collection<Student> getMembers() {
        return members;
    }

    public void setMembers(Collection<Student> members) {
        this.members = members;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(memberCount, course.memberCount) && Objects.equals(room, course.room) && Objects.equals(members, course.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberCount, room, members);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", memberCount='" + memberCount + '\'' +
                ", room='" + room + '\'' +
                ", members=" + members +
                '}';
    }
}
