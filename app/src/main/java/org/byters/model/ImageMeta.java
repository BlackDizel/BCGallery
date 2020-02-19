package org.byters.model;

public class ImageMeta {
    private final int id;
    private final String path;
    private ItemType type;

    public ImageMeta(int id, String path, ItemType type) {
        this.id = id;
        this.path = path;
        this.type = type;
    }

    public ItemType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
