package org.byters.api.view.presenter.listener;

public interface IPresenterListListener {
    void setContentExist(boolean isExist);

    void setButtonUpVisible(boolean parentFolderContentExist);
}
