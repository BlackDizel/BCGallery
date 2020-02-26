package org.byters.gallery;

import android.app.Application;

import org.byters.api.disccache.IPreferenceStorage;
import org.byters.api.memorycache.ICacheFolders;
import org.byters.api.memorycache.ICacheImages;
import org.byters.api.repository.IRepositoryImageDelete;
import org.byters.api.repository.IRepositoryImages;
import org.byters.api.repository.IRepositoryList;
import org.byters.api.repository.IRepositoryVideoDelete;
import org.byters.api.view.INavigator;
import org.byters.api.view.presenter.IPresenterAdapterFolderImages;
import org.byters.api.view.presenter.IPresenterFoldersAdapter;
import org.byters.api.view.presenter.IPresenterItemImage;
import org.byters.api.view.presenter.IPresenterItemVideo;
import org.byters.api.view.ui.IHelperPopup;
import org.byters.api.view.utils.IDeviceUtils;
import org.byters.gallery.disccache.PreferenceStorage;
import org.byters.gallery.memorycache.CacheFolders;
import org.byters.gallery.memorycache.CacheImages;
import org.byters.gallery.repository.RepositoryImageDelete;
import org.byters.gallery.repository.RepositoryImages;
import org.byters.gallery.repository.RepositoryList;
import org.byters.gallery.repository.RepositoryVideoDelete;
import org.byters.gallery.view.Navigator;
import org.byters.gallery.view.presenter.PresenterAdapterFolderImages;
import org.byters.gallery.view.presenter.PresenterFoldersAdapter;
import org.byters.gallery.view.presenter.PresenterItemImage;
import org.byters.gallery.view.presenter.PresenterItemVideo;
import org.byters.gallery.view.ui.util.HelperPopup;
import org.byters.gallery.view.util.DeviceUtils;

class Initializator {

    private Application application;
    private INavigator navigator;
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
    private IDeviceUtils deviceUtils;
    private IPresenterItemVideo presenterItemVideo;
    private IRepositoryVideoDelete repositoryVideoDelete;

    Initializator(Application application) {
        this.application = application;
    }

    INavigator getNavigator() {
        if (navigator == null) navigator = new Navigator();
        return navigator;
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

    public IDeviceUtils getDeviceUtils() {
        if (deviceUtils == null) deviceUtils = new DeviceUtils();
        return deviceUtils;
    }

    public IPresenterItemVideo getPresenterItemVideo() {
        if (presenterItemVideo == null) presenterItemVideo = new PresenterItemVideo();
        return presenterItemVideo;
    }

    public IRepositoryVideoDelete getRepositoryVideoDelete() {
        if (repositoryVideoDelete == null) repositoryVideoDelete = new RepositoryVideoDelete();
        return repositoryVideoDelete;
    }
}
