package org.byters.gallery.view.ui.util;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;

import org.byters.api.view.ui.utils.IThumbnailLoader;
import org.byters.api.view.ui.utils.listener.IImageLoaderListener;

import java.lang.ref.WeakReference;

public class ThumbnailLoader implements IThumbnailLoader {

    private Loader loader;

    private WeakReference<IImageLoaderListener> refListener;

    @Override
    public void load(ContentResolver contentResolver, int itemId) {

        if (loader != null) loader.cancel(true);

        loader = new Loader(contentResolver, refListener);
        loader.execute(itemId);

    }

    @Override
    public void setListener(IImageLoaderListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    private static class Loader extends AsyncTask<Integer, Void, Bitmap> {

        private final WeakReference<ContentResolver> refContentResolver;
        private final WeakReference<IImageLoaderListener> refListener;

        Loader(ContentResolver contentResolver, WeakReference<IImageLoaderListener> refListener) {
            this.refContentResolver = new WeakReference<>(contentResolver);
            this.refListener = refListener;
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            if (refContentResolver == null || refContentResolver.get() == null) return null;

            int itemId = params[0];

            return MediaStore.Images.Thumbnails.getThumbnail(
                    refContentResolver.get(), itemId,
                    MediaStore.Images.Thumbnails.MINI_KIND,
                    null);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (refListener == null || refListener.get() == null) return;
            refListener.get().onLoad(bitmap);
        }
    }

}
