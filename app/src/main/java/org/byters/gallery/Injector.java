package org.byters.gallery;

import android.app.Application;

import org.byters.gallery.memorycache.CacheList;
import org.byters.gallery.repository.RepositoryList;
import org.byters.gallery.view.Navigator;
import org.byters.gallery.view.presenter.PresenterAdapterList;
import org.byters.gallery.view.presenter.PresenterFragmentList;
import org.byters.gallery.view.ui.activity.MainActivity;
import org.byters.gallery.view.ui.adapter.AdapterList;
import org.byters.gallery.view.ui.fragment.FragmentItemImage;
import org.byters.gallery.view.ui.fragment.FragmentList;

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
    public void inject(AdapterList adapterList) {
        adapterList.presenter = links.getPresenterAdapterList();
    }

    @Override
    public void inject(FragmentList fragmentList) {
        fragmentList.presenter = links.getPresenterFragmentList();
    }

    @Override
    public void inject(PresenterAdapterList presenterAdapterList) {
        presenterAdapterList.cacheList = links.getCacheList();
        presenterAdapterList.navigator = links.getNavigator();
    }

    @Override
    public void inject(PresenterFragmentList presenterFragmentList) {
        presenterFragmentList.cacheList = links.getCacheList();
        presenterFragmentList.application = links.getApplication();
        presenterFragmentList.helperPopup = links.getHelperPopup();
        presenterFragmentList.preferenceStorage = links.getPreferenceStorage();
    }

    @Override
    public void inject(RepositoryList repositoryList) {
        repositoryList.cacheList = links.getCacheList();
        repositoryList.application = links.getApplication();
    }

    @Override
    public void inject(Navigator navigator) {
        navigator.helperPopup = links.getHelperPopup();
        navigator.preferenceStorage = links.getPreferenceStorage();
    }

    @Override
    public void inject(CacheList cacheList) {
        cacheList.preferenceStorage = links.getPreferenceStorage();
    }

    @Override
    public void inject(FragmentItemImage fragmentItemImage) {
        fragmentItemImage.presenter = links.getPresenterItemImage();
    }

}
