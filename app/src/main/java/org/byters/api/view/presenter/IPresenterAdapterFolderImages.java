package org.byters.api.view.presenter;

import org.byters.api.view.presenter.listener.IPresenterAdapterFolderImagesListener;

public interface IPresenterAdapterFolderImages {

    void onClickImage(int position);

    int getItemId(int position);

    int getItemsNum();

    void setListener(IPresenterAdapterFolderImagesListener listener);

    void onCreate(String folderId);
}
