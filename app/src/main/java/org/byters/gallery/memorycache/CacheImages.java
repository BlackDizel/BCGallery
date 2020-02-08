package org.byters.gallery.memorycache;

import android.net.Uri;

import org.byters.api.memorycache.ICacheImages;
import org.byters.api.memorycache.listener.ICacheImagesListener;
import org.byters.model.ImageMeta;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class CacheImages implements ICacheImages {

    private ArrayList<ImageMeta> data;
    private WeakReference<ICacheImagesListener> refListener;

    @Override
    public void setData(ArrayList<ImageMeta> result) {
        this.data = result;
        notifyListeners();
    }

    private void notifyListeners() {
        if (refListener == null || refListener.get() == null) return;
        refListener.get().onUpdate();
    }

    @Override
    public int getItemsNum() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public Uri getItemPath(int position) {
        return Uri.parse(data.get(position).getPath());
    }

    @Override
    public void setListener(ICacheImagesListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public void removeImageByPath(String url) {
        if (data == null) return;
        Iterator<ImageMeta> iterator = data.iterator();
        while (iterator.hasNext()) {
            ImageMeta item = iterator.next();
            if (item.getPath().equals(url)) {
                iterator.remove();
                notifyListeners();
                return;
            }
        }
    }
}
