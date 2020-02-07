package org.byters.model;

public class ImageMeta {
    private final int id;
    private final String path;

    public ImageMeta(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
