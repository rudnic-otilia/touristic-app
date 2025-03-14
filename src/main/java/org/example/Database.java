package org.example;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

//singleton
public class Database {
    private static Database databaseInstance;
    private Set<Museum> museums;
    private Set<Group> groups;

    //linkedHashSet pt a avea obiectele ordinate dupa ordinea in care sunt inserate.
    private Database() {
        museums = new LinkedHashSet<Museum>();
        groups = new LinkedHashSet<Group>();
    }

    public static Database getInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
        } else {
            databaseInstance.groups.clear();
        }
        return databaseInstance;
    }

    public void addMuseum(Museum museum) {
        museums.add(museum);
    }

    public void addMuseums(Set<Museum> museums) {
        this.museums.addAll(museums);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addGroups(Set<Group> groups) {
        this.groups.addAll(groups);
    }

    //getteri
    public Set<Museum> getMuseums() {
        return museums;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}

