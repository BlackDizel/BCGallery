package org.byters.api;

import org.byters.gallery.repository.RepositoryList;
import org.byters.gallery.view.Navigator;
import org.byters.gallery.view.presenter.PresenterAdapterList;
import org.byters.gallery.view.presenter.PresenterFragmentList;
import org.byters.gallery.view.ui.activity.MainActivity;
import org.byters.gallery.view.ui.adapter.AdapterList;
import org.byters.gallery.view.ui.fragment.FragmentList;

public interface IInjector {
    void inject(MainActivity mainActivity);

    void inject(AdapterList adapterList);

    void inject(FragmentList fragmentList);

    void inject(PresenterAdapterList presenterAdapterList);

    void inject(PresenterFragmentList presenterFragmentList);

    void inject(RepositoryList repositoryList);

    void inject(Navigator navigator);
}
