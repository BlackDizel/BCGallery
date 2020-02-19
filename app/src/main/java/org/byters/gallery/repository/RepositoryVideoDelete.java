package org.byters.gallery.repository;

import android.app.Application;
import android.provider.MediaStore;

import org.byters.api.memorycache.ICacheImages;
import org.byters.api.repository.listener.IRepositoryVideoDeleteListener;
import org.byters.gallery.GalleryApplication;

import java.lang.ref.WeakReference;

public class RepositoryVideoDelete implements org.byters.api.repository.IRepositoryVideoDelete {

    public Application application;
    public ICacheImages cacheImages;

    private WeakReference<IRepositoryVideoDeleteListener> refListener;

    public RepositoryVideoDelete() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void request(String url) {

        int rowsDeleted = application.getContentResolver()
                .delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        String.format("\"%s\"=\"%s\"", MediaStore.Images.Media.DATA, url), null);

        if (rowsDeleted == 1) {
            cacheImages.removeImageByPath(url);
            notifyListenersSuccess();
        }
    }

    private void notifyListenersSuccess() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onVideoDelete();
    }


    @Override
    public void setListener(IRepositoryVideoDeleteListener listener) {
        this.refListener = new WeakReference<>(listener);
    }
}
