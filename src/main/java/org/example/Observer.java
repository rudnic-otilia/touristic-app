package org.example;

import java.io.File;

public interface Observer {
    void update(String museumName, long museumCode, String message, File outputFile);
}
