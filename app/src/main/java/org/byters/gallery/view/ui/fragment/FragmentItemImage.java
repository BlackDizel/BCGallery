package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.byters.api.view.presenter.IPresenterItemImage;
import org.byters.api.view.presenter.listener.IPresenterItemImageListener;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.view.imageViewZoom.CropIwaImageView;

public class FragmentItemImage extends Fragment implements View.OnClickListener {

    private static final String EXTRA_IMAGE_URL = "IMAGE_URL";
    public IPresenterItemImage presenter;
    private CropIwaImageView ivItem;
    private View ivSettings;
    private View llSettings;
    private View tvRotate, tvCrop, tvDelete, tvShare;
    private IPresenterItemImageListener listenerPresenter;

    public void setArgs(Uri url) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_IMAGE_URL, url.toString());
        this.setArguments(bundle);
    }

    private String getImagePath() {
        return getArguments() == null ? null : getArguments().getString(EXTRA_IMAGE_URL, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GalleryApplication.getInjector().inject(this);
        presenter.setListener(listenerPresenter = new PresenterListener());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onCreateView(getImagePath());
    }

    private void initViews(View view) {
        ivItem = view.findViewById(R.id.ivItem);
        ivSettings = view.findViewById(R.id.ivSettings);
        llSettings = view.findViewById(R.id.llSettings);
        tvShare = view.findViewById(R.id.tvShare);
        tvDelete = view.findViewById(R.id.tvDelete);
        tvRotate = view.findViewById(R.id.tvRotate);
        tvCrop = view.findViewById(R.id.tvCrop);

        ivSettings.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        tvRotate.setOnClickListener(this);
        tvCrop.setOnClickListener(this);

        //todo impement settings view close
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivSettings)
            presenter.onClickSettings();
        if (v.getId() == R.id.tvShare)
            presenter.onClickShare();
        if (v.getId() == R.id.tvDelete)
            presenter.onClickDelete();
        if (v.getId() == R.id.tvRotate)
            presenter.onClickRotate();
        if (v.getId() == R.id.tvCrop)
            presenter.onClickCrop();
    }

    private class PresenterListener implements IPresenterItemImageListener {

        @Override
        public void setImage(Uri url) {
            ivItem.setImageURI(url);
        }

        @Override
        public void setSettingsVisible(boolean isVisible) {
            llSettings.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }
}
