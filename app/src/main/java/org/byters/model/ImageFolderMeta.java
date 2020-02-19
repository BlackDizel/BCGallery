package org.byters.model;

public final class ImageFolderMeta {
    private final String id;
    private final String name;
    private final int thumbnailId;
    private ItemType type;

    public ImageFolderMeta(String id, String name, int thumbnailId, ItemType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.thumbnailId = thumbnailId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getThumbnailId() {
        return thumbnailId;
    }

    public ItemType getType() {
        return type;
    }
}
