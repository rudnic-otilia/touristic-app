package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Professor extends Person implements Observer {
    private int experience;
    private String school;

    public Professor() {}

    public Professor(String surname, String name, String role, int experience, String school) {
        super(surname, name, role);
        this.experience = experience;
        this.school = school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSchool() {
        return school;
    }

    public int getExperience() {
        return experience;
    }

    public String toString() {
        return super.toString() +
                ", school=" + school +
                ", experience=" + experience;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return experience == professor.experience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience);
    }

//observer.
    public void update(String museumName, long museumCode, String message, File outputFile) {
        try (FileWriter writer = new FileWriter(outputFile, true)) {
            writer.write("To: " + this.getEmail() + " ## Message: " + museumName + " (" + museumCode + ") " + message + "\n");
        } catch (IOException e) {
            System.err.println("Eroare " + e.getMessage());
        }
    }

}
