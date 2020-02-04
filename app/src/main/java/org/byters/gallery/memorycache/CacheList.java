package org.byters.gallery.memorycache;

import org.byters.api.disccache.IPreferenceStorage;
import org.byters.api.memorycache.ICacheList;
import org.byters.api.memorycache.listener.ICacheListListener;
import org.byters.gallery.GalleryApplication;
import org.byters.model.PreferenceEnum;

import java.io.File;
import java.util.WeakHashMap;

public class CacheList implements ICacheList {

    private WeakHashMap<String, ICacheListListener> refListener;
    private File file;

    public IPreferenceStorage preferenceStorage;

    public CacheList() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public int getItemsNum() {
        return file == null || file.list() == null ? 0 : file.list().length;
    }

    @Override
    public void addListener(ICacheListListener listener) {
        if (refListener == null) refListener = new WeakHashMap<>();
        refListener.put(listener.getClass().getName(), listener);
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
        preferenceStorage.setString(file.getAbsolutePath(), PreferenceEnum.FOLDER);
        notifyListeners();
    }

    @Override
    public File getItem(int position) {
        return file == null || file.listFiles() == null || position < 0 || position >= file.listFiles().length
                ? null
                : file.listFiles()[position];
    }

    private void notifyListeners() {
        if (refListener == null) return;
        for (String key : refListener.keySet())
            if (refListener.get(key) != null)
                refListener.get(key).onUpdate();

    }
}
