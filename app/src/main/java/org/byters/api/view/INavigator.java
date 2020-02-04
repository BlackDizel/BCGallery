package org.byters.api.view;

import android.app.FragmentManager;
import android.content.Context;

import org.byters.gallery.view.ui.activity.MainActivity;

public interface INavigator {
    void set(Context context, FragmentManager manager, int layoutId);

    void navigateList();

    void navigateImage(String url);

    void navigateError();
}
