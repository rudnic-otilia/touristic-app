package org.example;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Person> members;
    private Professor guide;
    private int museumCode;
    private String timetable;

    public Group() {
        this.members = new ArrayList<>();
    }

    public void addGuide(Professor guide) {
        this.guide = guide;
    }

    public Professor getGuide() {
        return guide;
    }

    public void setGuide(Professor guide) {
        this.guide = guide;
    }

    public void addMember(Person member) {
        this.members.add(member);
    }

    public void removeMember(Person member) {
            this.members.remove(member);
    }

    public void setMuseumCode(int museumCode) {
        this.museumCode = museumCode;
    }

    public int getMuseumCode() {
        return museumCode;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getTimetable() {
        return timetable;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void resetGuide() {
        this.guide = null;
    }

}
