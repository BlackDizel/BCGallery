package org.byters.gallery.repository;

import android.app.Application;
import android.database.Cursor;
import android.provider.MediaStore;

import org.byters.api.memorycache.ICacheFolders;
import org.byters.gallery.GalleryApplication;
import org.byters.model.ImageFolderMeta;

import java.util.ArrayList;

public class RepositoryList implements org.byters.api.repository.IRepositoryList {

    public ICacheFolders cacheList;
    public Application application;

    public RepositoryList() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void request() {

        String projection[] = {MediaStore.Images.Media.BUCKET_ID, MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Thumbnails._ID};

        Cursor cursor = application.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null, null);

        ArrayList<ImageFolderMeta> result = new ArrayList<>();

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
            int thumbnailId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));

            if (isContains(result, id))
                continue;
            result.add(new ImageFolderMeta(id, name, thumbnailId));
        }

        cursor.close();

        cacheList.setData(result);

    }

    private boolean isContains(ArrayList<ImageFolderMeta> result, String id) {
        if (result == null || result.isEmpty()) return false;
        for (ImageFolderMeta item : result)
            if (item.getId().equals(id))
                return true;
        return false;
    }
}
