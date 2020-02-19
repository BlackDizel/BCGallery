package org.byters.gallery.memorycache;

import org.byters.api.memorycache.ICacheFolders;
import org.byters.api.memorycache.listener.ICacheFoldersListener;
import org.byters.model.ImageFolderMeta;
import org.byters.model.ItemType;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CacheFolders implements ICacheFolders {

    private ArrayList<ImageFolderMeta> data;
    private WeakReference<ICacheFoldersListener> refListener;

    @Override
    public int getItemsNum() {
        return data == null ? 0 : data.size();
    }

    @Override
    public String getFolderId(int position) {
        return data.get(position).getId();
    }

    @Override
    public String getItemTitle(int position) {
        return data.get(position).getName();
    }

    @Override
    public void setListener(ICacheFoldersListener listener) {
        this.refListener = new WeakReference<>(listener);

    }

    @Override
    public void setData(ArrayList<ImageFolderMeta> result) {
        this.data = result;
        notifyListeners();
    }

    @Override
    public int getItemThumbnailId(int position) {
        return data.get(position).getThumbnailId();
    }

    @Override
    public ItemType getItemType(int position) {
        return data.get(position).getType();
    }

    private void notifyListeners() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onUpdate();
    }
}
