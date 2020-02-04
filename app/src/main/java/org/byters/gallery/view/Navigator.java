package org.byters.gallery.view;

import android.app.FragmentManager;
import android.content.Context;

import org.byters.api.disccache.IPreferenceStorage;
import org.byters.api.view.INavigator;
import org.byters.api.view.ui.IHelperPopup;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.view.ui.fragment.FragmentError;
import org.byters.gallery.view.ui.fragment.FragmentItemImage;
import org.byters.gallery.view.ui.fragment.FragmentList;

import java.lang.ref.WeakReference;

public class Navigator implements INavigator {

    public IHelperPopup helperPopup;
    public IPreferenceStorage preferenceStorage;

    public Navigator() {
        GalleryApplication.getInjector().inject(this);
    }

    private WeakReference<Context> refContext;
    private WeakReference<FragmentManager> refManager;
    private int layoutId;

    @Override
    public void set(Context context, FragmentManager manager, int layoutId) {
        this.refContext = new WeakReference<>(context);
        this.refManager = new WeakReference<>(manager);
        this.layoutId = layoutId;
        helperPopup.set(context);
        preferenceStorage.set(context);
    }

    @Override
    public void navigateList() {
        if (refManager == null || refManager.get() == null) return;
        refManager.get().beginTransaction()
                .replace(layoutId, new FragmentList())
                .commit();
    }

    @Override
    public void navigateImage(String url) {
        if (refManager == null || refManager.get() == null) return;

        FragmentItemImage fragment = new FragmentItemImage();
        fragment.setArgs(url);
        refManager.get()
                .beginTransaction()
                .addToBackStack(null)
                .replace(layoutId, fragment)
                .commit();
    }

    @Override
    public void navigateError() {
        if (refManager == null || refManager.get() == null) return;
        refManager.get().beginTransaction()
                .replace(layoutId, new FragmentError())
                .commit();
    }
}
