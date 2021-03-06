package org.byters.gallery.view.presenter;

import android.net.Uri;

import org.byters.api.memorycache.ICacheImages;
import org.byters.api.repository.IRepositoryImageDelete;
import org.byters.api.repository.listener.IRepositoryImageDeleteListener;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.listener.IPresenterItemImageListener;
import org.byters.api.view.utils.IDeviceUtils;
import org.byters.gallery.GalleryApplication;
import org.byters.model.ItemType;

import java.lang.ref.WeakReference;

public class PresenterItemImage implements org.byters.api.view.presenter.IPresenterItemImage {

    private final IRepositoryImageDeleteListener listenerRepository;

    public IRepositoryImageDelete repositoryImageDelete;
    public ICacheImages cacheImages;
    public INavigator navigator;
    public IDeviceUtils deviceUtils;

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
        deviceUtils.shareImage(imagePath);
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
    public void onClickEdit() {
        deviceUtils.editImage(imagePath);
    }

    @Override
    public void onClickPrev() {
        int position = cacheImages.getImagePosition(imagePath);
        Uri uri = cacheImages.getItemPath(--position);
        if (uri == null) return;

        ItemType type = cacheImages.getItemType(position);
        if (type == ItemType.TYPE_IMAGE)
            navigator.navigateImage(uri, false);
        else if (type == ItemType.TYPE_VIDEO)
            navigator.navigateVideo(uri, false);

    }

    @Override
    public void onClickNext() {
        int position = cacheImages.getImagePosition(imagePath);
        Uri uri = cacheImages.getItemPath(++position);
        if (uri == null) return;

        ItemType type = cacheImages.getItemType(position);
        if (type == ItemType.TYPE_IMAGE)
            navigator.navigateImage(uri, false);
        else if (type == ItemType.TYPE_VIDEO)
            navigator.navigateVideo(uri, false);
    }

    @Override
    public void onCreateView(String imagePath) {
        this.imagePath = Uri.parse(imagePath);
        notifyListenerButtonsView(!isImageFirst(this.imagePath), !isImageLast(this.imagePath));
        notifyListenerImage();
    }

    private void notifyListenerButtonsView(boolean isViewFirst, boolean isViewLast) {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().setNavigationButtonsVisible(isViewFirst, isViewLast);
    }

    private boolean isImageFirst(Uri imagePath) {
        return cacheImages.getImagePosition(imagePath) == 0;
    }

    private boolean isImageLast(Uri imagePath) {
        return cacheImages.getImagePosition(imagePath) == cacheImages.getItemsNum() - 1;
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
