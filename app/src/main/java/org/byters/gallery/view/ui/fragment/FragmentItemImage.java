package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.byters.api.view.presenter.IPresenterItemImage;
import org.byters.api.view.presenter.listener.IPresenterItemImageListener;
import org.byters.api.view.ui.dialog.listener.IDialogImageSettingsListener;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.dialog.DialogImageSettings;
import org.byters.gallery.view.ui.view.imageViewZoom.CropIwaImageView;

public class FragmentItemImage extends Fragment implements View.OnClickListener {

    private static final String EXTRA_IMAGE_URL = "IMAGE_URL";
    public IPresenterItemImage presenter;
    private CropIwaImageView ivItem;
    private View ivSettings;
    private IPresenterItemImageListener listenerPresenter;
    private DialogImageSettings dialogImageSettings;
    private IDialogImageSettingsListener listenerDialogSettings;

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
        listenerDialogSettings = new ListenerDialogSettings();
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
        ivSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivSettings)
            presenter.onClickSettings();
    }

    private class PresenterListener implements IPresenterItemImageListener {

        @Override
        public void setImage(Uri url) {
            if (!isAdded()) return;
            ivItem.setImageURI(url);
        }

        @Override
        public void setSettingsVisible(boolean isVisible) {
            if (!isAdded()) return;

            if (!isVisible) return;

            if (dialogImageSettings != null)
                dialogImageSettings.cancel();

            dialogImageSettings = new DialogImageSettings(getActivity(), listenerDialogSettings);
            dialogImageSettings.show();

        }

        @Override
        public void onImageDelete() {
            if (!isAdded()) return;
            getActivity().onBackPressed();
        }
    }

    private class ListenerDialogSettings implements IDialogImageSettingsListener {

        @Override
        public void onClickShare() {
            presenter.onClickShare();
        }

        @Override
        public void onClickDelete() {
            presenter.onClickDelete();
        }

        @Override
        public void onClickRotate() {
            presenter.onClickRotate();
        }

        @Override
        public void onClickCrop() {
            presenter.onClickCrop();
        }

        @Override
        public void onClickEdit() {
            presenter.onClickEdit();
        }
    }

}
