package org.byters.gallery.view.presenter;

import android.net.Uri;

import org.byters.api.repository.IRepositoryImageDelete;
import org.byters.api.repository.listener.IRepositoryImageDeleteListener;
import org.byters.api.view.presenter.listener.IPresenterItemImageListener;
import org.byters.gallery.GalleryApplication;

import java.lang.ref.WeakReference;

public class PresenterItemImage implements org.byters.api.view.presenter.IPresenterItemImage {

    private final IRepositoryImageDeleteListener listenerRepository;
    public IRepositoryImageDelete repositoryImageDelete;
    private WeakReference<IPresenterItemImageListener> refListener;
    private Uri imagePath;

    public PresenterItemImage() {
        GalleryApplication.getInjector().inject(this);
        repositoryImageDelete.setListener(listenerRepository = new ListenerRepository());
    }

    @Override
    public void setListener(IPresenterItemImageListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public void onClickSettings() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().setSettingsVisible(true);
    }

    @Override
    public void onClickShare() {

    }

    @Override
    public void onClickDelete() {
        repositoryImageDelete.request(imagePath.toString());
    }

    @Override
    public void onClickRotate() {

    }

    @Override
    public void onClickCrop() {

    }

    @Override
    public void onCreateView(String imagePath) {
        this.imagePath = Uri.parse(imagePath);
        notifyListenerImage();
    }

    private void notifyListenerImage() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().setImage(imagePath);
    }

    private void notifyListenerImageDeleted() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onImageDelete();
    }

    private class ListenerRepository implements IRepositoryImageDeleteListener {
        @Override
        public void onImageDelete() {
            notifyListenerImageDeleted();
        }

        @Override
        public void onImageDeleteError() {

        }
    }
}
