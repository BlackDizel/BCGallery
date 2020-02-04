package org.byters.gallery.repository;

import android.app.Application;

import org.byters.api.memorycache.ICacheList;
import org.byters.gallery.GalleryApplication;

public class RepositoryList implements org.byters.api.repository.IRepositoryList {

    public ICacheList cacheList;
    public Application application;

    public RepositoryList() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void request() {

/*        String[] projection = { MediaStore.Images.ImageColumns.DATA};
                Cursor cursor = application.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null);

        ArrayList<String> result = new ArrayList<>();

        while(cursor.moveToNext())
            result.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));


        cursor.close();

        cacheList.setData(result);*/


    }
}
