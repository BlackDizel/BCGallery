package org.byters.gallery.view.presenter;

import android.net.Uri;

import org.byters.api.memorycache.ICacheList;
import org.byters.api.memorycache.listener.ICacheListListener;
import org.byters.api.repository.IRepositoryList;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.listener.IPresenterListAdapterListener;
import org.byters.gallery.GalleryApplication;

import java.io.File;
import java.lang.ref.WeakReference;

public class PresenterAdapterList implements org.byters.api.view.presenter.IPresenterListAdapter {

    private static final int TYPE_DIRECTORY = 0;
    private static final int TYPE_FILE = 1;
    private final ICacheListListener listenerCache;
    public INavigator navigator;
    public ICacheList cacheList;
    public IRepositoryList repositoryList;

    private WeakReference<IPresenterListAdapterListener> refListener;

    public PresenterAdapterList() {
        GalleryApplication.getInjector().inject(this);
        cacheList.addListener(listenerCache = new ListenerCache());
    }

    @Override
    public void setListener(IPresenterListAdapterListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public int getItemsNum() {
        return cacheList.getItemsNum();
    }

    @Override
    public Uri getItemUrl(int position) {
        return Uri.fromFile(cacheList.getItem(position));
    }

    @Override
    public void onClickImage(int position) {
        navigator.navigateImage(getItemUrl(position).toString());
    }

    @Override
    public int getItemType(int position) {
        File file = cacheList.getItem(position);
        return file == null || file.isFile() ? TYPE_FILE : TYPE_DIRECTORY;
    }

    @Override
    public boolean isTypeImage(int position) {
        return getItemType(position) == TYPE_FILE;
    }

    @Override
    public String getItemTitle(int position) {
        File file = cacheList.getItem(position);
        return file == null ? "unknown" : file.getName();
    }

    @Override
    public void onClickFolder(int position) {
        cacheList.setFile(cacheList.getItem(position));
    }

    private class ListenerCache implements ICacheListListener {
        @Override
        public void onUpdate() {
            if (refListener == null || refListener.get() == null) return;
            refListener.get().onUpdate();
        }
    }
}
