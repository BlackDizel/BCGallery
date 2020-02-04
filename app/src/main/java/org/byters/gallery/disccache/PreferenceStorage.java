package org.byters.gallery.disccache;

import android.content.Context;

import org.byters.model.PreferenceEnum;

import java.lang.ref.WeakReference;

public class PreferenceStorage implements org.byters.api.disccache.IPreferenceStorage {

    private static final String PREFERENCE_NAME = "pref_app";
    private WeakReference<Context> refContext;

    @Override
    public void set(Context context) {
        this.refContext = new WeakReference<>(context);
    }

    @Override
    public String readString(PreferenceEnum item) {
        if (refContext == null || refContext.get() == null) return null;
        return refContext.get()
                .getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(item.getTitle(), null);
    }

    @Override
    public void setString(String value, PreferenceEnum item) {
        if (refContext == null || refContext.get() == null) return;
        refContext.get()
                .getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(item.getTitle(), value)
                .commit();
    }

}
