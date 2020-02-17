package org.byters.api.memorycache;

import android.net.Uri;

import org.byters.api.memorycache.listener.ICacheImagesListener;
import org.byters.model.ImageMeta;

import java.util.ArrayList;

public interface ICacheImages {
    void setData(ArrayList<ImageMeta> result);

    int getItemsNum();

    int getItemId(int position);

    Uri getItemPath(int position);

    void setListener(ICacheImagesListener iCacheImagesListener);

    void removeImageByPath(String url);

    int getImagePosition(Uri imagePath);
}
