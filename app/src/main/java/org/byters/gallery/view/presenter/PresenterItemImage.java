package org.byters.gallery.view.presenter;

import android.net.Uri;

import org.byters.api.view.presenter.listener.IPresenterItemImageListener;

import java.lang.ref.WeakReference;

public class PresenterItemImage implements org.byters.api.view.presenter.IPresenterItemImage {

    private WeakReference<IPresenterItemImageListener> refListener;
    private Uri imagePath;

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
}
