package org.byters.gallery.view.ui.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.byters.api.view.ui.utils.listener.IImageLoaderListener;

import java.io.File;
import java.lang.ref.WeakReference;

public class ImageLoader implements org.byters.api.view.ui.utils.IImageLoader {

    private WeakReference<IImageLoaderListener> refListener;
/*
    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }*/

    private Loader loader;

    @Override
    public void setListener(IImageLoaderListener listener) {
        this.refListener = new WeakReference<>(listener);
    }

    @Override
    public void load(File item) {

        if (loader != null) loader.cancel(true);

        loader = new Loader();
        loader.execute(item.getAbsolutePath());


    }

    private class Loader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            String path = strings[0];

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            //options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inSampleSize = 4;
            options.inJustDecodeBounds = false;

            return BitmapFactory.decodeFile(path, options);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (refListener == null || refListener.get() == null) return;
            refListener.get().onLoad(bitmap);
        }
    }


}
