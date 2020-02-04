package org.byters.api.view.presenter;

import org.byters.api.view.presenter.listener.IPresenterListListener;

public interface IPresenterList {
    void onCreateView();

    void onClickParent();

    void setListener(IPresenterListListener listener);
}
