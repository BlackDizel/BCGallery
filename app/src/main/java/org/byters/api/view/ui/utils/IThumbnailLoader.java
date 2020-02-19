package org.byters.api.view.ui.utils;

import android.content.ContentResolver;

import org.byters.api.view.ui.utils.listener.IImageLoaderListener;
import org.byters.model.ItemType;

public interface IThumbnailLoader {

    void load(ContentResolver contentResolver, int itemId, ItemType itemType);

    void setListener(IImageLoaderListener listener);
}
