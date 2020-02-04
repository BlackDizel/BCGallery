package org.byters.gallery.view.ui.util;

import android.content.Context;
import android.widget.Toast;

import org.byters.api.view.ui.IHelperPopup;

import java.lang.ref.WeakReference;

public class HelperPopup implements IHelperPopup {

    private WeakReference<Context> refContext;

    @Override
    public void set(Context context) {
        this.refContext = new WeakReference<>(context);
    }

    @Override
    public void showMessage(int messageRes) {
        if (refContext == null || refContext.get() == null) return;
        Toast.makeText(refContext.get(), messageRes, Toast.LENGTH_SHORT).show();
    }
}
