package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FindGuideCommand implements Command {
    private final Database database;

    public FindGuideCommand(Database database) {
        this.database = database;
    }

    public void execute(String[] params, File outputFile) {
        try {
            String surname = params[1];
            String name = params[2];
            String actualRole = params[3].trim();
            String role = params[8];
            int age = Integer.parseInt(params[4]);
            String email = params[5].isEmpty() ? null : params[5];
            String school = params[6];
            int experience = Integer.parseInt(params[7]);

            Person guide;
            if (actualRole.equals("profesor")) {
                guide = new Professor(surname, name, role, experience, school);
            } else {
                guide = new Person(surname, name, role);
            }
            guide.setAge(age);
            guide.setEmail(email);

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
                Professor existingGuide = mainGroup.getGuide();
                if (existingGuide != null && existingGuide.equals(guide)) {
                    try (FileWriter writer = new FileWriter(outputFile, true)) {
                        writer.write(museumCode + " ## " + timetable + " ## guide found: " + existingGuide.toString() + "\n");
                    }
                } else {
                    try (FileWriter writer = new FileWriter(outputFile, true)) {
                        writer.write(museumCode + " ## " + timetable + " ## guide not exists: " + guide.toString() + "\n");
                    }
                }
            } else {
                try (FileWriter writer = new FileWriter(outputFile, true)) {
                    writer.write(museumCode + " ## " + timetable + " ## GroupNotExistsException: Group does not exist. ## (find guide: " + guide.toString() + ")\n");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Eroare la parsarea datelor: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }



}
