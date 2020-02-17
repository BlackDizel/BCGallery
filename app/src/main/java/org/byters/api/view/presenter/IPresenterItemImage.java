package org.byters.api.view.presenter;

import org.byters.api.view.presenter.listener.IPresenterItemImageListener;

public interface IPresenterItemImage {
    void setListener(IPresenterItemImageListener listener);

    void onClickSettings();

    void onClickShare();

    void onClickDelete();

    void onClickRotate();

    void onClickCrop();

    void onCreateView(String imagePath);

    void onClickEdit();
}
