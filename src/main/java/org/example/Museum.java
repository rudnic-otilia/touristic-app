package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//builder
public class Museum {
    //campuri obligatorii
    private String name;
    private long code;
    private long supervisorCode;
    private Location location;

    //campuri optionale
    private Person manager;
    private Integer foundingYear;
    private String phoneNumber;
    private String fax;
    private String email;
    private String url;
    private String profile;


    public void notifyObservers(String message, File outputFile, Set<Group> groups) {
        List<Group> groupList = new ArrayList<>();

        for (Group group : groups) {
            if (group.getMuseumCode() == code) {
                groupList.add(group);
            }
        }

        for (int i = groupList.size() - 1; i >= 0; i--) {
            Professor guide = groupList.get(i).getGuide();
            if (guide != null) {
                guide.update(name, code, message, outputFile);
            }
        }
    }


    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    static class Builder {

        private String name;
        private long code;
        private long supervisorCode;
        private Location location;

        private Person manager = null;
        private Integer foundingYear = null;
        private String phoneNumber = "";
        private String fax = "";
        private String email = "";
        private String url = "";
        private String profile = "";

        public Builder(String name, long code, long supervisorCode, Location location) {
            this.name = name;
            this.code = code;
            this.supervisorCode = supervisorCode;
            this.location = location;
        }

        public Builder manager(Person manager) {
            this.manager = manager;
            return this;
        }

        public Builder foundingYear(Integer foundingYear) {
            this.foundingYear = foundingYear;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder fax(String fax) {
            this.fax = fax;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder profile(String profile) {
            this.profile = profile;
            return this;
        }

        public Museum build() {
            return new Museum(this);
        }
    }

    private Museum(Builder builder) {
        this.name = builder.name;
        this.code = builder.code;
        this.supervisorCode = builder.supervisorCode;
        this.location = builder.location;
        this.manager = builder.manager;
        this.foundingYear = builder.foundingYear;
        this.phoneNumber = builder.phoneNumber;
        this.fax = builder.fax;
        this.email = builder.email;
        this.url = builder.url;
        this.profile = builder.profile;
    }

}
