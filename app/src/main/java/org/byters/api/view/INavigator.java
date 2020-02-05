package org.byters.api.view;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;

public interface INavigator {
    void set(Context context, FragmentManager manager, int layoutId);

    void navigateList();

    void navigateImage(Uri uri, boolean addToBackStack);

    void navigateError();
}
