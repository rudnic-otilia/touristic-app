package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class AddGuideCommand implements Command {
    private final Database database;

    public AddGuideCommand(Database database) {
        this.database = database;
    }

    public void execute(String[] params, File outputFile) {
        try {
            Person guide;
            String actualRole = params[3];
            int museumCode = Integer.parseInt(params[9]);
            String timetable = params[10];

            if (actualRole.equals("student")) {
                guide = new Student(params[1], params[2], params[8], params[6], Integer.parseInt(params[7]));
            } else {
                guide = new Professor(params[1], params[2], params[8], Integer.parseInt(params[7]), params[6]);
            }
            guide.setAge(Integer.parseInt(params[4]));
            guide.setEmail(params[5].isEmpty() ? null : params[5]);

            Group mainGroup = null;
            for (Group group : database.getGroups()) {
                if (group.getMuseumCode() == museumCode && group.getTimetable().equals(timetable)) {
                    mainGroup = group;
                    break;
                }
            }

            if (mainGroup != null) {
                if (!actualRole.equalsIgnoreCase("profesor")) {
                    try (FileWriter writer = new FileWriter(outputFile, true)) {
                        writer.write(museumCode + " ## " + timetable + " ## GuideTypeException: Guide must be a professor. ## (new guide: "
                                + guide.toString() + ")\n");
                    }
                    return;
                }

                if (mainGroup.getGuide() != null) {
                    Professor existingGuide = mainGroup.getGuide();
                    try (FileWriter writer = new FileWriter(outputFile, true)) {
                        writer.write(museumCode + " ## " + timetable + " ## GuideExistsException: Guide already exists. ## (new guide: "
                                + existingGuide.toString() + ")\n");
                    }
                    return;
                }

                mainGroup.addGuide((Professor) guide);
                try (FileWriter writer = new FileWriter(outputFile, true)) {
                    writer.write(museumCode + " ## " + timetable + " ## new guide: " + guide.toString() + "\n");
                }
            } else {
                Group newGroup = new Group();
                newGroup.setMuseumCode(museumCode);
                newGroup.setTimetable(timetable);
                newGroup.addGuide((Professor) guide);
                database.addGroup(newGroup);

                try (FileWriter writer = new FileWriter(outputFile, true)) {
                    writer.write(museumCode + " ## " + timetable + " ## new guide: " + guide.toString() + "\n");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Eroare: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Eroare:" + e.getMessage());
        }
    }
}

