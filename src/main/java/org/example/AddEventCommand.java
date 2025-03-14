package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddEventCommand implements Command {
    private final Database database;

    public AddEventCommand(Database database) {
        this.database = database;
    }

    public void execute(String[] params, File outputFile) {
        try {
            long museumCode = Long.parseLong(params[1]);
            String message = params[2];

            Museum museum = null;
            for (Museum m : database.getMuseums()) {
                if (m.getCode() == museumCode) {
                    museum = m;
                    break;
                }
            }

            if (museum != null) {
                museum.notifyObservers(message, outputFile, database.getGroups());
            } else {
                try (FileWriter writer = new FileWriter(outputFile, true)) {
                    writer.write("Error: Museum with code " + museumCode + " does not exist.\n");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.err.println("Eroare: " + e.getMessage());
        }
    }
}
