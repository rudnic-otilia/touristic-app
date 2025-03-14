package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddMemberCommand implements Command {
    private final Database database;

    public AddMemberCommand(Database database) {
        this.database = database;
    }

    public void execute(String[] params, File outputFile) {
        try {
            String surname = params[1];
            String name = params[2];
            String actualRole = params[3].trim();
            String role = params[8].trim();
            int age = Integer.parseInt(params[4]);
            String email = params[5].isEmpty() ? null : params[5];
            String school = params[6];
            int yearOrExperience = Integer.parseInt(params[7]);

            Person member;
            if (actualRole.equals("student")) {
                member = new Student(surname, name, role, school, yearOrExperience);
            } else if (actualRole.equals("profesor")) {
                member = new Professor(surname, name, role, yearOrExperience, school);
            } else {
                member = new Person(surname, name, role);
            }
            member.setAge(age);
            member.setEmail(email);

            int museumCode = Integer.parseInt(params[9]);
            String timetable = params[10];

            Group mainGroup = null;
            for (Group group : database.getGroups()) {
                if (group.getMuseumCode() == museumCode && group.getTimetable().equals(timetable)) {
                    mainGroup = group;
                    break;
                }
            }

            if (mainGroup != null) {
                if (mainGroup.getMembers().size() >= 10) {
                    try (FileWriter writer = new FileWriter(outputFile, true)) {
                        writer.write(museumCode + " ## " + timetable + " ## GroupThresholdException: Group cannot have more than " +
                                "10 members. ## (new member: " + member.toString() + ")\n");
                    }
                } else {
                    mainGroup.addMember(member);
                    try (FileWriter writer = new FileWriter(outputFile, true)) {
                        writer.write(museumCode + " ## " + timetable + " ## new member: " + member.toString() + "\n");
                    }
                }
            } else {
                try (FileWriter writer = new FileWriter(outputFile, true)) {
                    writer.write(museumCode + " ## " + timetable + " ## GroupNotExistsException: Group does not exist. ## (new member: " + member.toString() + ")\n");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Eroare:" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Eroare:" + e.getMessage());
        }
    }

}
