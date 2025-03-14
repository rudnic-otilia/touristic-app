package org.example;

public class Exceptions {

    public static class GroupNotExistsException extends Exception {
        public GroupNotExistsException() {
            super("GroupNotExistsException: Group does not exist.");
        }

        public GroupNotExistsException(String message) {
            super(message);
        }
    }

    public static class GroupThresholdException extends Exception {
        public GroupThresholdException() {
            super("GroupThresholdException: Group cannot have more than 10 members.");
        }

        public GroupThresholdException(String message) {
            super(message);
        }
    }

    public static class GuideExistsException extends Exception {
        public GuideExistsException() {
            super("GuideExistsException: Guide already exists.");
        }

        public GuideExistsException(String message) {
            super(message);
        }
    }

    public static class GuideTypeException extends Exception {
        public GuideTypeException() {
            super("GuideTypeException: Guide must be a professor.");
        }

        public GuideTypeException(String message) {
            super(message);
        }
    }

    public static class PersonNotExistsException extends Exception {
        public PersonNotExistsException() {
            super("PersonNotExistsException: Person was not found in the group.");
        }

        public PersonNotExistsException(String message) {
            super(message);
        }
    }
}

