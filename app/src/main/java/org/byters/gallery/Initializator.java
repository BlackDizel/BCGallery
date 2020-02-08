package org.byters.gallery;

import android.app.Application;

import org.byters.api.disccache.IPreferenceStorage;
import org.byters.api.memorycache.ICacheFolders;
import org.byters.api.memorycache.ICacheImages;
import org.byters.api.memorycache.ICacheList;
import org.byters.api.repository.IRepositoryImageDelete;
import org.byters.api.repository.IRepositoryImages;
import org.byters.api.repository.IRepositoryList;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.IPresenterAdapterFolderImages;
import org.byters.api.view.presenter.IPresenterFoldersAdapter;
import org.byters.api.view.presenter.IPresenterItemImage;
import org.byters.api.view.presenter.IPresenterList;
import org.byters.api.view.presenter.IPresenterListAdapter;
import org.byters.api.view.ui.IHelperPopup;
import org.byters.gallery.disccache.PreferenceStorage;
import org.byters.gallery.memorycache.CacheFolders;
import org.byters.gallery.memorycache.CacheImages;
import org.byters.gallery.memorycache.CacheList;
import org.byters.gallery.repository.RepositoryImageDelete;
import org.byters.gallery.repository.RepositoryImages;
import org.byters.gallery.repository.RepositoryList;
import org.byters.gallery.view.Navigator;
import org.byters.gallery.view.presenter.PresenterAdapterFolderImages;
import org.byters.gallery.view.presenter.PresenterAdapterList;
import org.byters.gallery.view.presenter.PresenterFoldersAdapter;
import org.byters.gallery.view.presenter.PresenterFragmentList;
import org.byters.gallery.view.presenter.PresenterItemImage;
import org.byters.gallery.view.ui.util.HelperPopup;

class Initializator {

    private Application application;
    private INavigator navigator;
    private IPresenterListAdapter presenterAdapterList;
    private IPresenterList presenterFragmentList;
    private ICacheList cacheList;
    private IRepositoryList repositoryList;
    private IHelperPopup helperPopup;
    private IPreferenceStorage preferenceStorage;
    private IPresenterItemImage presenterItemImage;
    private IPresenterFoldersAdapter presenterFoldersAdapter;
    private ICacheFolders cacheFolders;
    private ICacheImages cacheImages;
    private IPresenterAdapterFolderImages presenterAdapterFolderImages;
    private IRepositoryImages repositoryImages;
    private IRepositoryImageDelete repositoryImageDelete;

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

    public IPreferenceStorage getPreferenceStorage() {
        if (preferenceStorage == null) preferenceStorage = new PreferenceStorage();
        return preferenceStorage;
    }

    public IPresenterItemImage getPresenterItemImage() {
        if (presenterItemImage == null) presenterItemImage = new PresenterItemImage();
        return presenterItemImage;
    }

    public IPresenterFoldersAdapter getPresenterAdapterFolders() {
        if (presenterFoldersAdapter == null)
            presenterFoldersAdapter = new PresenterFoldersAdapter();
        return presenterFoldersAdapter;
    }

    public ICacheFolders getCacheFolders() {
        if (cacheFolders == null) cacheFolders = new CacheFolders();
        return cacheFolders;
    }

    public ICacheImages getCacheImages() {
        if (cacheImages == null) cacheImages = new CacheImages();
        return cacheImages;
    }

    public IPresenterAdapterFolderImages getPresenterADapterFolderImages() {
        if (presenterAdapterFolderImages == null)
            presenterAdapterFolderImages = new PresenterAdapterFolderImages();
        return presenterAdapterFolderImages;
    }

    public IRepositoryImages getRepositoryImages() {
        if (repositoryImages == null) repositoryImages = new RepositoryImages();
        return repositoryImages;
    }

    public IRepositoryImageDelete getRepositoryImageDelete() {
        if (repositoryImageDelete == null) repositoryImageDelete = new RepositoryImageDelete();
        return repositoryImageDelete;
    }
}
