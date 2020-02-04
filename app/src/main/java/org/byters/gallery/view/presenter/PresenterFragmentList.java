package org.byters.gallery.view.presenter;

import android.app.Application;
import android.os.Environment;

import org.byters.api.memorycache.ICacheList;
import org.byters.gallery.GalleryApplication;

import java.io.File;

public class PresenterFragmentList implements org.byters.api.view.presenter.IPresenterList {

    public ICacheList cacheList;
    public Application application;

    public PresenterFragmentList() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void onCreateView() {
        checkCacheFile();
    }

    private void checkCacheFile() {
        if (cacheList.getFile() != null) return;
        cacheList.setFile(application.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    @Override
    public void onClickParent() {
        File file = cacheList.getFile();
        if (file == null) return;

        File parent = file.getParentFile();
        if (parent == null) return;

        cacheList.setFile(parent);
    }

}
