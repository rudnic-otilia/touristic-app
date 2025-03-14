package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddMuseumCommand implements Command {
    private final Database database;

    public AddMuseumCommand(Database database) {
        this.database = database;
    }

    public void execute(String[] params, File outputFile) {
        try {
            Location location = new Location.Builder(params[3], Integer.parseInt(params[16]))
                    .setLocality(params[4])
                    .setAdminUnit(params[5])
                    .setAddress(params[6])
                    .setLatitude(parseIntegerOrDefault(params[18], 0))
                    .setLongitude(parseIntegerOrDefault(params[19], 0))
                    .build();

            String[] nameParts = params[13].split(" ", 2); //imparte in nume si prenume
            String surname = nameParts.length > 1 ? nameParts[1] : "";
            String name = nameParts[0];
            Person manager = params[13].isEmpty() ? null : new Person(surname, name, "Manager");

            Museum museum = new Museum.Builder(params[2], Long.parseLong(params[1]), Long.parseLong(params[14]), location)
                    .manager(manager)
                    .foundingYear(parseIntegerOrDefault(params[10], null))
                    .phoneNumber(params[7])
                    .fax(params[8])
                    .email(params[11])
                    .url(params[12])
                    .profile(params[15])
                    .build();

            database.addMuseum(museum);

            try (FileWriter writer = new FileWriter(outputFile, true)) {
                writer.write(museum.getCode() + ": " + museum.getName() + "\n");
            }

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            try (FileWriter writer = new FileWriter(outputFile, true)) {
                writer.write("Exception: Data is broken. ## (" + String.join("|", params) + ")\n");
            } catch (IOException ioException) {
                System.err.println("Eroare: " + ioException.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Eroare :" + e.getMessage());
        }
    }


    private Integer parseIntegerOrDefault(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
