package org.byters.gallery.repository;

import android.app.Application;
import android.database.Cursor;
import android.provider.MediaStore;

import org.byters.api.memorycache.ICacheFolders;
import org.byters.gallery.GalleryApplication;
import org.byters.model.ImageFolderMeta;
import org.byters.model.ItemType;

import java.util.ArrayList;

public class RepositoryList implements org.byters.api.repository.IRepositoryList {

    public ICacheFolders cacheList;
    public Application application;

    public RepositoryList() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void request() {

        ArrayList<ImageFolderMeta> result = new ArrayList<>();

        addImages(result);
        addVideo(result);
        sortByDate(result);

        cacheList.setData(result);

    }

    private void sortByDate(ArrayList<ImageFolderMeta> result) {
        //todo
    }

    private void addImages(ArrayList<ImageFolderMeta> result) {


        String projection[] = {MediaStore.Images.Media.BUCKET_ID, MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Thumbnails._ID};

        Cursor cursor = application.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
            int thumbnailId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));

            if (isContains(result, id))
                continue;
            result.add(new ImageFolderMeta(id, name, thumbnailId, ItemType.TYPE_IMAGE));
        }

        cursor.close();
    }

    private void addVideo(ArrayList<ImageFolderMeta> result) {

        String projection[] = {MediaStore.Video.Media.BUCKET_ID, MediaStore.Video.Media.BUCKET_DISPLAY_NAME, MediaStore.Video.Thumbnails._ID};

        Cursor cursor = application.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
            int thumbnailId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails._ID));

            if (isContains(result, id))
                continue;
            result.add(new ImageFolderMeta(id, name, thumbnailId, ItemType.TYPE_VIDEO));
        }

        cursor.close();
    }

    private boolean isContains(ArrayList<ImageFolderMeta> result, String id) {
        if (result == null || result.isEmpty()) return false;
        for (ImageFolderMeta item : result)
            if (item.getId().equals(id))
                return true;
        return false;
    }
}
