package org.byters.api.disccache;

import android.content.Context;

import org.byters.model.PreferenceEnum;

public interface IPreferenceStorage {
    void set(Context context);

    String readString(PreferenceEnum item);

    void setString(String value, PreferenceEnum item);
}
