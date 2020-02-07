package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.byters.gallery.R;
import org.byters.gallery.view.ui.adapter.AdapterFolders;

public class FragmentFolders extends Fragment {

    private AdapterFolders adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folders, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        GridView lvItems = view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapter = new AdapterFolders());
    }
}
