package org.byters.api.view.ui.utils;

import android.content.ContentResolver;

import org.byters.api.view.ui.utils.listener.IImageLoaderListener;

public interface IThumbnailLoader {

    void load(ContentResolver contentResolver, int itemId);

    void setListener(IImageLoaderListener listener);
}
