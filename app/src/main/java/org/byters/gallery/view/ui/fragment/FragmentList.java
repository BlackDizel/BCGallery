package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.byters.api.view.presenter.IPresenterList;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.adapter.AdapterList;

public class FragmentList extends Fragment implements View.OnClickListener {

    public IPresenterList presenter;
    private ListView lvItems;
    private AdapterList adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initViews(view);
        presenter.onCreateView();
        return view;
    }

    private void initViews(View view) {
        lvItems = view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapter = new AdapterList());
        view.findViewById(R.id.ivParent).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivParent)
            presenter.onClickParent();
    }
}
