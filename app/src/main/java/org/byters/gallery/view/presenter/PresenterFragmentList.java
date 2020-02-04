package org.byters.gallery.view.presenter;

import android.app.Application;
import android.os.Environment;

import org.byters.api.memorycache.ICacheList;
import org.byters.api.memorycache.listener.ICacheListListener;
import org.byters.api.view.presenter.listener.IPresenterListListener;
import org.byters.api.view.ui.IHelperPopup;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;

import java.io.File;
import java.lang.ref.WeakReference;

public class PresenterFragmentList implements org.byters.api.view.presenter.IPresenterList {

    public ICacheList cacheList;
    public Application application;
    public IHelperPopup helperPopup;

    private WeakReference<IPresenterListListener> refListener;
    private CacheListListener listenerCache;

    public PresenterFragmentList() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void onCreateView() {
        cacheList.addListener(listenerCache = new CacheListListener());
        checkCacheFile();
    }

    private void checkCacheFile() {
        if (cacheList.getFile() != null) return;
        cacheList.setFile(application.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    @Override
    public void onClickParent() {
        File file = cacheList.getFile();

        if (!isParentFolderContentExist(file)) {
            helperPopup.showMessage(R.string.parent_files_empty);
            return;
        }

        cacheList.setFile(file.getParentFile());
    }

    private boolean isParentFolderContentExist(File file) {
        if (file == null) return false;
        File parent = file.getParentFile();
        return parent != null && parent.listFiles() != null && parent.listFiles().length > 0;
    }

    @Override
    public void setListener(IPresenterListListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    private class CacheListListener implements ICacheListListener {
        @Override
        public void onUpdate() {
            if (refListener == null || refListener.get() == null) return;
            refListener.get().setContentExist(cacheList.getItemsNum() > 0);

            refListener.get().setButtonUpVisible(isParentFolderContentExist(cacheList.getFile()));
        }
    }
}
