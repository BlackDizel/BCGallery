package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.byters.api.view.utils.IDeviceUtils;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.adapter.AdapterFolders;

public class FragmentFolders extends Fragment implements View.OnClickListener {

    public IDeviceUtils deviceUtils;
    private AdapterFolders adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GalleryApplication.getInjector().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folders, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        GridView lvItems = view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapter = new AdapterFolders());

        view.findViewById(R.id.ivFeedback).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivFeedback)
            deviceUtils.openLink(getActivity().getString(R.string.discord));
    }
}
