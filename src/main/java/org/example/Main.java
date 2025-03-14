package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        if (args.length == 2 || args.length == 4) {
            String pathType = args[0];
            try {
                if (args.length == 2) {
                    processFile(args[1], database);
                } else if (args.length == 4) {
                    processFile(args[1], database); //Fisier muzee
                    processFile(args[2], database); //Fisier grupuri
                    processFile(args[3], database); //Fisier evenimente
                }
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void processFile(String fileName, Database database) throws FileNotFoundException {
        //nu e pusa extensia
        if (!fileName.endsWith(".in")) {
            fileName += ".in";
        }

        File outputFile = new File(fileName.replace(".in", ".out"));

        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(fileName);
        }
        //citim din fisier
        try (Scanner scanner = new Scanner(file)) {
            //flag pentru a ignora prima linie
            boolean isFirstLine = true;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //ignoram prima linie.
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                //facem split la parti.
                String[] parts = line.split("\\|");

                //determinam comanda.
                Command command;
                switch (parts[0]) {
                    case "ADD MUSEUM":
                        command = new AddMuseumCommand(database);
                        break;
                    case "ADD GUIDE":
                        command = new AddGuideCommand(database);
                        break;
                    case "ADD MEMBER" :
                        command = new AddMemberCommand(database);
                        break;
                    case "REMOVE MEMBER" :
                        command = new RemoveMemberCommand(database);
                        break;
                    case "REMOVE GUIDE" :
                        command = new RemoveGuideCommand(database);
                        break;
                    case "FIND GUIDE" :
                        command = new FindGuideCommand(database);
                        break;
                    case "FIND MEMBER" :
                        command = new FindMemberCommand(database);
                        break;
                    case "ADD EVENT" :
                        command = new AddEventCommand(database);
                        break;
                    default:
                        System.out.println("Comanda necunoscuta: " + parts[0]);
                        continue;
                }
                command.execute(parts, outputFile);
            }
        }
    }
}

