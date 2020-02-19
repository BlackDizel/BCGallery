package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.byters.api.view.presenter.IPresenterItemVideo;
import org.byters.api.view.presenter.listener.IPresenterItemVideoListener;
import org.byters.api.view.ui.dialog.listener.IDialogImageSettingsListener;
import org.byters.api.view.ui.utils.IThumbnailLoader;
import org.byters.api.view.ui.utils.listener.IImageLoaderListener;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.dialog.DialogImageSettings;
import org.byters.gallery.view.ui.util.ThumbnailLoader;
import org.byters.model.ItemType;

public class FragmentItemVideo extends Fragment implements View.OnClickListener {

    private static final String EXTRA_IMAGE_URL = "IMAGE_URL";
    public IPresenterItemVideo presenter;
    private ImageView ivItem;
    private View ivSettings, ivPrev, ivNext;
    private DialogImageSettings dialogImageSettings;
    private IDialogImageSettingsListener listenerDialogSettings;
    private PresenterListener listenerPresente;
    private IThumbnailLoader loader;
    private ListenerLoader listenerLoader;

    public void setArgs(Uri url) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_IMAGE_URL, url.toString());
        this.setArguments(bundle);
    }

    private String getVideoPath() {
        return getArguments() == null ? null : getArguments().getString(EXTRA_IMAGE_URL, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GalleryApplication.getInjector().inject(this);
        listenerDialogSettings = new ListenerDialogSettings();
        presenter.setListener(listenerPresente = new PresenterListener());
        loader = new ThumbnailLoader();
        loader.setListener(listenerLoader = new ListenerLoader());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video
                , container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated(getVideoPath());
    }

    private void initViews(View view) {
        ivItem = view.findViewById(R.id.ivItem);
        ivSettings = view.findViewById(R.id.ivSettings);
        ivPrev = view.findViewById(R.id.ivPrev);
        ivNext = view.findViewById(R.id.ivNext);
        ivSettings.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPrev.setOnClickListener(this);
        ivItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivSettings)
            presenter.onClickSettings();
        if (v.getId() == R.id.ivPrev)
            presenter.onClickPrev();
        if (v.getId() == R.id.ivNext)
            presenter.onClickNext();
        if (v.getId() == R.id.ivItem)
            presenter.onClickItem();
    }

    private class PresenterListener implements IPresenterItemVideoListener {

        @Override
        public void setImage(int id) {
            if (!isAdded()) return;
            loader.load(getActivity().getContentResolver(), id, ItemType.TYPE_VIDEO);
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

        @Override
        public void setNavigationButtonsVisible(boolean isViewPrev, boolean isViewNext) {
            if (!isAdded()) return;
            ivPrev.setVisibility(isViewPrev ? View.VISIBLE : View.GONE);
            ivNext.setVisibility(isViewNext ? View.VISIBLE : View.GONE);
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

    private class ListenerLoader implements IImageLoaderListener {
        @Override
        public void onLoad(Bitmap bitmap) {
            if (!isAdded()) return;
            ivItem.setImageBitmap(bitmap);
        }
    }
}
