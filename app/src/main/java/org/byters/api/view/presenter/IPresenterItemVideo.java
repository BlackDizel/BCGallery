package org.byters.api.view.presenter;

import org.byters.api.view.presenter.listener.IPresenterItemVideoListener;

public interface IPresenterItemVideo {
    void onViewCreated(String imagePath);

    void onClickSettings();

    void onClickPrev();

    void onClickNext();

    void onClickItem();

    void onClickShare();

    void onClickDelete();

    void onClickRotate();

    void onClickCrop();

    void onClickEdit();

    void setListener(IPresenterItemVideoListener listener);
}
