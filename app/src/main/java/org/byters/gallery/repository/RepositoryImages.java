package org.byters.gallery.repository;

import android.app.Application;
import android.database.Cursor;
import android.provider.MediaStore;

import org.byters.api.memorycache.ICacheImages;
import org.byters.gallery.GalleryApplication;
import org.byters.model.ImageMeta;
import org.byters.model.ItemType;

import java.util.ArrayList;

public class RepositoryImages implements org.byters.api.repository.IRepositoryImages {

    public ICacheImages cacheImages;
    public Application application;

    public RepositoryImages() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void request(String folder_id) {

        ArrayList<ImageMeta> result = new ArrayList<>();

        addImages(folder_id, result);
        addVideo(folder_id, result);
        sortByDate(result);

        cacheImages.setData(result);
    }

    private void sortByDate(ArrayList<ImageMeta> result) {
        //todo implement
    }

    private void addImages(String folder_id, ArrayList<ImageMeta> result) {

        String projection[] = {MediaStore.Images.Media.DATA, MediaStore.Images.Thumbnails._ID};

        Cursor cursor = application.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Images.Media.BUCKET_ID + " like ? ",
                new String[]{folder_id},
                null);

        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
            result.add(new ImageMeta(id, path, ItemType.TYPE_IMAGE));
        }

        cursor.close();
    }

    private void addVideo(String folder_id, ArrayList<ImageMeta> result) {

        String projection[] = {MediaStore.Video.Media.DATA, MediaStore.Video.Thumbnails._ID};

        Cursor cursor = application.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Images.Media.BUCKET_ID + " like ? ",
                new String[]{folder_id},
                null);

        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails._ID));
            result.add(new ImageMeta(id, path, ItemType.TYPE_VIDEO));
        }

        cursor.close();
    }
}
