package org.byters.api;

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

public interface IInjector {
    void inject(MainActivity mainActivity);

    void inject(RepositoryList repositoryList);

    void inject(Navigator navigator);

    void inject(FragmentItemImage fragmentItemImage);

    void inject(AdapterFolders adapterFolders);

    void inject(PresenterFoldersAdapter presenterFoldersAdapter);

    void inject(RepositoryImages repositoryImages);

    void inject(AdapterFolderImages adapterFolderImages);

    void inject(PresenterAdapterFolderImages presenterAdapterFolderImages);

    void inject(PresenterItemImage presenterItemImage);

    void inject(RepositoryImageDelete repositoryImageDelete);

    void inject(FragmentItemVideo fragmentItemVideo);

    void inject(PresenterItemVideo presenterItemVideo);

    void inject(RepositoryVideoDelete repositoryVideoDelete);

    void inject(FragmentFolders fragmentFolders);
}
