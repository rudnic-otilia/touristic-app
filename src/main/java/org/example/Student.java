package org.example;

import java.util.Objects;

public class Student extends Person {
    private String school;
    private int studyYear;

    public Student() {}

    public Student(String surname, String name, String role, String school, int studyYear) {
        super(surname, name, role);
        this.school = school;
        this.studyYear = studyYear;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public String toString() {
        return super.toString() +
                ", school=" + school +
                ", studyYear=" + studyYear;
    }

    //pt a compara corect obiectele.
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studyYear == student.studyYear;
    }

    //se asigura ca obiectele egala au acelasi hash.
    public int hashCode() {
        return Objects.hash(super.hashCode(), studyYear);
    }

}
