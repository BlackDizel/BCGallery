package org.byters.gallery;

import android.app.Application;

import org.byters.api.memorycache.ICacheList;
import org.byters.api.repository.IRepositoryList;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.IPresenterList;
import org.byters.api.view.presenter.IPresenterListAdapter;
import org.byters.api.view.ui.IHelperPopup;
import org.byters.gallery.memorycache.CacheList;
import org.byters.gallery.repository.RepositoryList;
import org.byters.gallery.view.Navigator;
import org.byters.gallery.view.presenter.PresenterAdapterList;
import org.byters.gallery.view.presenter.PresenterFragmentList;
import org.byters.gallery.view.ui.util.HelperPopup;

class Initializator {

    private Application application;
    private INavigator navigator;
    private IPresenterListAdapter presenterAdapterList;
    private IPresenterList presenterFragmentList;
    private ICacheList cacheList;
    private IRepositoryList repositoryList;
    private IHelperPopup helperPopup;

    Initializator(Application application) {
        this.application = application;
    }

    INavigator getNavigator() {
        if (navigator == null) navigator = new Navigator();
        return navigator;
    }

    public IPresenterListAdapter getPresenterAdapterList() {
        if (presenterAdapterList == null) presenterAdapterList = new PresenterAdapterList();
        return presenterAdapterList;
    }

    public IPresenterList getPresenterFragmentList() {
        if (presenterFragmentList == null) presenterFragmentList = new PresenterFragmentList();
        return presenterFragmentList;
    }

    public ICacheList getCacheList() {
        if (cacheList == null) cacheList = new CacheList();
        return cacheList;
    }

    public IRepositoryList getRepositoryList() {
        if (repositoryList == null) repositoryList = new RepositoryList();
        return repositoryList;
    }

    public Application getApplication() {
        return application;
    }

    public IHelperPopup getHelperPopup() {
        if (helperPopup == null) helperPopup = new HelperPopup();
        return helperPopup;
    }
}
