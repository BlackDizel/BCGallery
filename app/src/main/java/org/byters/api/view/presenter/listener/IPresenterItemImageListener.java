package org.byters.api.view.presenter.listener;

import android.net.Uri;

public interface IPresenterItemImageListener {

    void setImage(Uri url);

    void setSettingsVisible(boolean isVisible);

    void onImageDelete();
}
