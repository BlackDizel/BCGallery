package org.byters.api.view.utils;

import android.content.Context;
import android.net.Uri;

public interface IDeviceUtils {
    void shareImage(Uri imagePath);

    void set(Context context);

    void editImage(Uri imagePath);
}
