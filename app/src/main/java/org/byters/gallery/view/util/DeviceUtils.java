package org.byters.gallery.view.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.lang.ref.WeakReference;

public class DeviceUtils implements org.byters.api.view.utils.IDeviceUtils {

    private WeakReference<Context> refContext;

    @Override
    public void shareImage(Uri imagePath) {
        if (refContext == null || refContext.get() == null) return;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, imagePath);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        refContext.get().startActivity(Intent.createChooser(intent, null));
    }

    @Override
    public void set(Context context) {
        this.refContext = new WeakReference<>(context);
    }

    @Override
    public void editImage(Uri imagePath) {
        if (refContext == null || refContext.get() == null) return;

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.putExtra(Intent.EXTRA_STREAM, imagePath);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        refContext.get().startActivity(Intent.createChooser(intent, null));
    }

    @Override
    public void navigateVideo(Uri itemPath) {

        if (refContext == null || refContext.get() == null) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra(Intent.EXTRA_STREAM, itemPath);
        intent.setType("video/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        refContext.get().startActivity(Intent.createChooser(intent, null));
    }
}
