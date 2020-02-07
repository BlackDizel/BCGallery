package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.byters.gallery.R;
import org.byters.gallery.view.ui.adapter.AdapterFolderImages;

public class FragmentFolderImages extends Fragment
        implements View.OnClickListener {

    private static final String EXTRA_FOLDER_ID = "EXTRA_FOLDER_ID";
    private AdapterFolderImages adapter;

    public static Fragment getInstance(String folderId) {
        Fragment fragment = new FragmentFolderImages();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_FOLDER_ID, folderId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder_images, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        GridView lvItems = view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapter = new AdapterFolderImages(getFolderId()));
        view.findViewById(R.id.ivBack).setOnClickListener(this);
    }

    private String getFolderId() {
        return getArguments().getString(EXTRA_FOLDER_ID);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack)
            getActivity().onBackPressed();
    }
}
