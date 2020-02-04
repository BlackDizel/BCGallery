package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.byters.api.view.presenter.IPresenterList;
import org.byters.api.view.presenter.listener.IPresenterListListener;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.adapter.AdapterList;

public class FragmentList extends Fragment implements View.OnClickListener {

    public IPresenterList presenter;
    private ListView lvItems;
    private AdapterList adapter;
    private ListenerPresenter listenerPresenter;
    private TextView tvMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GalleryApplication.getInjector().inject(this);
        presenter.setListener(listenerPresenter = new ListenerPresenter());
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
        tvMessage = view.findViewById(R.id.tvMessage);
        view.findViewById(R.id.ivParent).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivParent)
            presenter.onClickParent();
    }

    private class ListenerPresenter implements IPresenterListListener {
        @Override
        public void setContentExist(boolean isExist) {
            lvItems.setVisibility(isExist ? View.VISIBLE : View.GONE);
            tvMessage.setVisibility(isExist ? View.GONE : View.VISIBLE);
            tvMessage.setText(isExist ? R.string.empty : R.string.no_content);
        }
    }
}
