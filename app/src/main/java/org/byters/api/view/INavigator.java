package org.byters.api.view;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;

public interface INavigator {
    void set(Context context, FragmentManager manager, int layoutId);

    void navigateImage(Uri uri, boolean addToBackStack);

    void navigateError();

    void navigateFolder(String folderId);

    void navigateFolderList();

    void navigateVideo(Uri uri, boolean addToBackstack);
}
