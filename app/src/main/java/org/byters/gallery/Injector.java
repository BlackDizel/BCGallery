package org.byters.gallery;

import android.app.Application;

import org.byters.gallery.repository.RepositoryImageDelete;
import org.byters.gallery.repository.RepositoryImages;
import org.byters.gallery.repository.RepositoryList;
import org.byters.gallery.repository.RepositoryVideoDelete;
import org.byters.gallery.view.Navigator;
import org.byters.gallery.view.presenter.PresenterAdapterFolderImages;
import org.byters.gallery.view.presenter.PresenterFoldersAdapter;
import org.byters.gallery.view.presenter.PresenterItemImage;
import org.byters.gallery.view.presenter.PresenterItemVideo;
import org.byters.gallery.view.ui.activity.MainActivity;
import org.byters.gallery.view.ui.adapter.AdapterFolderImages;
import org.byters.gallery.view.ui.adapter.AdapterFolders;
import org.byters.gallery.view.ui.fragment.FragmentFolders;
import org.byters.gallery.view.ui.fragment.FragmentItemImage;
import org.byters.gallery.view.ui.fragment.FragmentItemVideo;

class Injector implements org.byters.api.IInjector {

    private Initializator links;

    Injector(Application application) {
        links = new Initializator(application);
    }

    @Override
    public void inject(MainActivity mainActivity) {
        mainActivity.navigator = links.getNavigator();
    }

    @Override
    public void inject(RepositoryList repositoryList) {
        repositoryList.cacheList = links.getCacheFolders();
        repositoryList.application = links.getApplication();
    }

    @Override
    public void inject(Navigator navigator) {
        navigator.helperPopup = links.getHelperPopup();
        navigator.preferenceStorage = links.getPreferenceStorage();
        navigator.deviceUtils = links.getDeviceUtils();
    }

    @Override
    public void inject(FragmentItemImage fragmentItemImage) {
        fragmentItemImage.presenter = links.getPresenterItemImage();
    }

    @Override
    public void inject(AdapterFolders adapterFolders) {
        adapterFolders.presenter = links.getPresenterAdapterFolders();
    }

    @Override
    public void inject(PresenterFoldersAdapter presenterFoldersAdapter) {
        presenterFoldersAdapter.cacheFolders = links.getCacheFolders();
        presenterFoldersAdapter.navigator = links.getNavigator();
        presenterFoldersAdapter.repositoryList = links.getRepositoryList();
    }

    @Override
    public void inject(RepositoryImages repositoryImages) {
        repositoryImages.application = links.getApplication();
        repositoryImages.cacheImages = links.getCacheImages();
    }

    @Override
    public void inject(AdapterFolderImages adapterFolderImages) {
        adapterFolderImages.presenter = links.getPresenterADapterFolderImages();
    }

    @Override
    public void inject(PresenterAdapterFolderImages presenterAdapterFolderImages) {
        presenterAdapterFolderImages.cacheImages = links.getCacheImages();
        presenterAdapterFolderImages.navigator = links.getNavigator();
        presenterAdapterFolderImages.repositoryImages = links.getRepositoryImages();
    }

    @Override
    public void inject(PresenterItemImage presenterItemImage) {
        presenterItemImage.repositoryImageDelete = links.getRepositoryImageDelete();
        presenterItemImage.deviceUtils = links.getDeviceUtils();
        presenterItemImage.cacheImages = links.getCacheImages();
        presenterItemImage.navigator = links.getNavigator();
    }

    @Override
    public void inject(RepositoryImageDelete repositoryImageDelete) {
        repositoryImageDelete.cacheImages = links.getCacheImages();
        repositoryImageDelete.application = links.getApplication();
    }

    @Override
    public void inject(FragmentItemVideo fragmentItemVideo) {
        fragmentItemVideo.presenter = links.getPresenterItemVideo();
    }

    @Override
    public void inject(PresenterItemVideo presenterItemVideo) {
        presenterItemVideo.deviceUtils = links.getDeviceUtils();
        presenterItemVideo.navigator = links.getNavigator();
        presenterItemVideo.cacheImages = links.getCacheImages();
        presenterItemVideo.repositoryDelete = links.getRepositoryVideoDelete();
    }

    @Override
    public void inject(RepositoryVideoDelete repositoryVideoDelete) {
        repositoryVideoDelete.cacheImages = links.getCacheImages();
        repositoryVideoDelete.application = links.getApplication();
    }

    @Override
    public void inject(FragmentFolders fragmentFolders) {
        fragmentFolders.deviceUtils = links.getDeviceUtils();
    }

}
