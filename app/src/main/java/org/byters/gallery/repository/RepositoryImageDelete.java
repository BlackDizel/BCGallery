package org.byters.gallery.repository;

import android.app.Application;
import android.provider.MediaStore;

import org.byters.api.memorycache.ICacheImages;
import org.byters.api.repository.IRepositoryImageDelete;
import org.byters.api.repository.listener.IRepositoryImageDeleteListener;
import org.byters.gallery.GalleryApplication;

import java.lang.ref.WeakReference;

public class RepositoryImageDelete implements IRepositoryImageDelete {

    public Application application;
    public ICacheImages cacheImages;

    private WeakReference<IRepositoryImageDeleteListener> refListener;

    public RepositoryImageDelete() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void request(String url) {

        String[] params = {MediaStore.Images.Media.DATA, url};
        int rowsDeleted = application.getContentResolver()
                .delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        String.format("\"%s\"=\"%s\"", MediaStore.Images.Media.DATA, url), null);

        if (rowsDeleted == 1) {
            cacheImages.removeImageByPath(url);
            notifyListenersSuccess();
        } else
            notifyListenersError();
    }

    private void notifyListenersError() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onImageDeleteError();
    }

    private void notifyListenersSuccess() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onImageDelete();
    }

    @Override
    public void setListener(IRepositoryImageDeleteListener listener) {
        this.refListener = new WeakReference<>(listener);
    }
}
