package org.byters.gallery.memorycache;

import org.byters.api.memorycache.ICacheList;
import org.byters.api.memorycache.listener.ICacheListListener;

import java.io.File;
import java.lang.ref.WeakReference;

public class CacheList implements ICacheList {

    private WeakReference<ICacheListListener> refListener;
    private File file;

    @Override
    public int getItemsNum() {
        return file == null || file.list() == null ? 0 : file.list().length;
    }

    @Override
    public void addListener(ICacheListListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
        notifyListeners();
    }

    @Override
    public File getItem(int position) {
        return file == null || file.listFiles() == null || position < 0 || position >= file.listFiles().length
                ? null
                : file.listFiles()[position];
    }

    private void notifyListeners() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onUpdate();

    }
}
