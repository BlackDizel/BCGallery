package org.byters.gallery.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;

import org.byters.api.disccache.IPreferenceStorage;
import org.byters.api.view.INavigator;
import org.byters.api.view.ui.IHelperPopup;
import org.byters.api.view.utils.IDeviceUtils;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.view.ui.fragment.FragmentError;
import org.byters.gallery.view.ui.fragment.FragmentFolderImages;
import org.byters.gallery.view.ui.fragment.FragmentFolders;
import org.byters.gallery.view.ui.fragment.FragmentItemImage;
import org.byters.gallery.view.ui.fragment.FragmentItemVideo;

import java.lang.ref.WeakReference;

public class Navigator implements INavigator {

    public IHelperPopup helperPopup;
    public IPreferenceStorage preferenceStorage;
    public IDeviceUtils deviceUtils;

    private WeakReference<Context> refContext;
    private WeakReference<FragmentManager> refManager;
    private int layoutId;

    public Navigator() {
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public void set(Context context, FragmentManager manager, int layoutId) {
        this.refContext = new WeakReference<>(context);
        this.refManager = new WeakReference<>(manager);
        this.layoutId = layoutId;
        helperPopup.set(context);
        preferenceStorage.set(context);
        deviceUtils.set(context);
    }

    @Override
    public void navigateImage(Uri uri, boolean addToBackStack) {
        if (refManager == null || refManager.get() == null) return;

        FragmentItemImage fragment = new FragmentItemImage();
        fragment.setArgs(uri);
        FragmentTransaction transaction = refManager.get()
                .beginTransaction();

        if (addToBackStack)
            transaction = transaction.addToBackStack(null);

        transaction.replace(layoutId, fragment)
                .commit();
    }

    @Override
    public void navigateError() {
        if (refManager == null || refManager.get() == null) return;
        refManager.get().beginTransaction()
                .replace(layoutId, new FragmentError())
                .commit();
    }

    @Override
    public void navigateFolder(String folderId) {
        if (refManager == null || refManager.get() == null) return;
        refManager.get().beginTransaction()
                .replace(layoutId, FragmentFolderImages.getInstance(folderId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateFolderList() {
        if (refManager == null || refManager.get() == null) return;
        refManager.get().beginTransaction()
                .replace(layoutId, new FragmentFolders())
                .commit();
    }

    @Override
    public void navigateVideo(Uri uri, boolean addToBackstack) {
        if (refManager == null || refManager.get() == null) return;

        FragmentItemVideo fragment = new FragmentItemVideo();
        fragment.setArgs(uri);
        FragmentTransaction transaction = refManager.get()
                .beginTransaction();

        if (addToBackstack)
            transaction = transaction.addToBackStack(null);

        transaction.replace(layoutId, fragment)
                .commit();
    }
}
