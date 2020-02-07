package org.byters.gallery.view.presenter;

import org.byters.api.memorycache.ICacheImages;
import org.byters.api.memorycache.listener.ICacheImagesListener;
import org.byters.api.repository.IRepositoryImages;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.IPresenterAdapterFolderImages;
import org.byters.api.view.presenter.listener.IPresenterAdapterFolderImagesListener;
import org.byters.gallery.GalleryApplication;

import java.lang.ref.WeakReference;

public class PresenterAdapterFolderImages implements IPresenterAdapterFolderImages {

    private final ICacheImagesListener listenerCache;
    public IRepositoryImages repositoryImages;
    public ICacheImages cacheImages;
    public INavigator navigator;
    private WeakReference<IPresenterAdapterFolderImagesListener> refListener;

    public PresenterAdapterFolderImages() {
        GalleryApplication.getInjector().inject(this);
        cacheImages.setListener(listenerCache = new ListenerCache());
    }

    @Override
    public void onClickImage(int position) {
        navigator.navigateImage(cacheImages.getItemPath(position), true);
    }

    @Override
    public int getItemId(int position) {
        return cacheImages.getItemId(position);
    }

    @Override
    public int getItemsNum() {
        return cacheImages.getItemsNum();
    }

    @Override
    public void setListener(IPresenterAdapterFolderImagesListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public void onCreate(String folderId) {
        repositoryImages.request(folderId);
    }

    private class ListenerCache implements ICacheImagesListener {
        @Override
        public void onUpdate() {
            if (refListener == null || refListener.get() == null) refListener.get().onUpdate();
        }
    }
}
