package org.byters.api.view.presenter;

import org.byters.api.view.presenter.listener.IPresenterListAdapterListener;
import org.byters.model.ItemType;

public interface IPresenterFoldersAdapter {
    void setListener(IPresenterListAdapterListener listener);

    void onCreate();

    int getItemsNum();

    void onClickFolder(int position);

    String getItemTitle(int position);

    int getItemThumbnailId(int position);

    ItemType getItemType(int position);
}
