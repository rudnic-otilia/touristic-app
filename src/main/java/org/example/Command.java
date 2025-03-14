package org.example;

import java.io.File;

public interface Command {
    void execute(String[] params, File outputFile);
}
