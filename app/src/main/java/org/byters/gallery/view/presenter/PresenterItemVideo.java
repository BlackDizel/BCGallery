package org.byters.gallery.view.presenter;

import android.net.Uri;

import org.byters.api.memorycache.ICacheImages;
import org.byters.api.repository.IRepositoryVideoDelete;
import org.byters.api.repository.listener.IRepositoryVideoDeleteListener;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.listener.IPresenterItemVideoListener;
import org.byters.api.view.utils.IDeviceUtils;
import org.byters.gallery.GalleryApplication;
import org.byters.model.ItemType;

import java.lang.ref.WeakReference;

public class PresenterItemVideo implements org.byters.api.view.presenter.IPresenterItemVideo {
    private final IRepositoryVideoDeleteListener listenerRepository;

    public IRepositoryVideoDelete repositoryDelete;
    public ICacheImages cacheImages;
    public INavigator navigator;
    public IDeviceUtils deviceUtils;

    private WeakReference<IPresenterItemVideoListener> refListener;
    private Uri imagePath;

    public PresenterItemVideo() {
        GalleryApplication.getInjector().inject(this);
        repositoryDelete.setListener(listenerRepository = new ListenerRepository());
    }

    @Override
    public void setListener(IPresenterItemVideoListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public void onClickSettings() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().setSettingsVisible(true);
    }

    @Override
    public void onClickShare() {
        deviceUtils.shareVideo(imagePath);
    }

    @Override
    public void onClickDelete() {
        repositoryDelete.request(imagePath.toString());
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
    public void onClickItem() {
        deviceUtils.navigateVideo(imagePath);
    }

    @Override
    public void onViewCreated(String imagePath) {
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
        refListener.get().setImage(cacheImages.getItemIdByPath(imagePath));
    }

    private void notifyListenerItemDeleted() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onImageDelete();
    }

    private class ListenerRepository implements IRepositoryVideoDeleteListener {
        @Override
        public void onVideoDelete() {
            notifyListenerItemDeleted();
        }
    }
}
