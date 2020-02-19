package org.byters.api.view.presenter.listener;

public interface IPresenterItemVideoListener {
    void setImage(int id);

    void setSettingsVisible(boolean isVisible);

    void onImageDelete();

    void setNavigationButtonsVisible(boolean isViewPrev, boolean isViewNext);
}
