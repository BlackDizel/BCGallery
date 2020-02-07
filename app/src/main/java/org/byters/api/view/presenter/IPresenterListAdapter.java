package org.byters.api.view.presenter;

import android.net.Uri;

import org.byters.api.view.presenter.listener.IPresenterListAdapterListener;

import java.io.File;

public interface IPresenterListAdapter {
    void setListener(IPresenterListAdapterListener listener);

    int getItemsNum();

    Uri getItemUrl(int position);

    void onClickImage(int position);

    int getItemType(int position);

    boolean isTypeImage(int position);

    String getItemTitle(int position);

    void onClickFolder(int position);

    File getItem(int position);
}
