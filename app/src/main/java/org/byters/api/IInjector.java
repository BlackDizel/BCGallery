package org.byters.api;

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
import org.byters.gallery.view.ui.activity.MainActivity;
import org.byters.gallery.view.ui.adapter.AdapterFolderImages;
import org.byters.gallery.view.ui.adapter.AdapterFolders;
import org.byters.gallery.view.ui.adapter.AdapterList;
import org.byters.gallery.view.ui.fragment.FragmentItemImage;
import org.byters.gallery.view.ui.fragment.FragmentList;

public interface IInjector {
    void inject(MainActivity mainActivity);

    void inject(AdapterList adapterList);

    void inject(FragmentList fragmentList);

    void inject(PresenterAdapterList presenterAdapterList);

    void inject(PresenterFragmentList presenterFragmentList);

    void inject(RepositoryList repositoryList);

    void inject(Navigator navigator);

    void inject(CacheList cacheList);

    void inject(FragmentItemImage fragmentItemImage);

    void inject(AdapterFolders adapterFolders);

    void inject(PresenterFoldersAdapter presenterFoldersAdapter);

    void inject(RepositoryImages repositoryImages);

    void inject(AdapterFolderImages adapterFolderImages);

    void inject(PresenterAdapterFolderImages presenterAdapterFolderImages);

    void inject(PresenterItemImage presenterItemImage);

    void inject(RepositoryImageDelete repositoryImageDelete);
}
