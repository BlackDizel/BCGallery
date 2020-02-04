package org.byters.model;

public enum PreferenceEnum {
    FOLDER("folder");

    private final String title;

    PreferenceEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
