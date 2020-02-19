package org.byters.gallery.view.presenter;

import org.byters.api.memorycache.ICacheFolders;
import org.byters.api.memorycache.listener.ICacheFoldersListener;
import org.byters.api.repository.IRepositoryList;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.listener.IPresenterListAdapterListener;
import org.byters.gallery.GalleryApplication;
import org.byters.model.ItemType;

import java.lang.ref.WeakReference;

public class PresenterFoldersAdapter implements org.byters.api.view.presenter.IPresenterFoldersAdapter {

    private final ListenerCache listenerCache;
    public IRepositoryList repositoryList;
    public ICacheFolders cacheFolders;
    public INavigator navigator;

    private WeakReference<IPresenterListAdapterListener> refListener;

    public PresenterFoldersAdapter() {
        GalleryApplication.getInjector().inject(this);
        cacheFolders.setListener(listenerCache = new ListenerCache());
    }

    @Override
    public void setListener(IPresenterListAdapterListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public void onCreate() {
        repositoryList.request();
    }

    @Override
    public int getItemsNum() {
        return cacheFolders.getItemsNum();
    }

    @Override
    public void onClickFolder(int position) {
        navigator.navigateFolder(cacheFolders.getFolderId(position));
    }

    @Override
    public String getItemTitle(int position) {
        return cacheFolders.getItemTitle(position);
    }

    @Override
    public int getItemThumbnailId(int position) {
        return cacheFolders.getItemThumbnailId(position);
    }

    @Override
    public ItemType getItemType(int position) {
        return cacheFolders.getItemType(position);
    }

    private class ListenerCache implements ICacheFoldersListener {
        @Override
        public void onUpdate() {
            if (refListener == null || refListener.get() == null) return;
            refListener.get().onUpdate();
        }
    }
}
