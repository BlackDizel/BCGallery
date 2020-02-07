package org.byters.gallery.view.ui.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.byters.api.view.presenter.IPresenterAdapterFolderImages;
import org.byters.api.view.presenter.listener.IPresenterAdapterFolderImagesListener;
import org.byters.api.view.ui.adapter.viewholder.IViewHolder;
import org.byters.api.view.ui.utils.IThumbnailLoader;
import org.byters.api.view.ui.utils.listener.IImageLoaderListener;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.util.ThumbnailLoader;

public class AdapterFolderImages extends BaseAdapter {

    private final ListenerPresenter listenerPresenter;

    public IPresenterAdapterFolderImages presenter;

    public AdapterFolderImages(String folderId) {
        GalleryApplication.getInjector().inject(this);
        presenter.setListener(listenerPresenter = new ListenerPresenter());
        presenter.onCreate(folderId);
    }

    @Override
    public int getCount() {
        return presenter.getItemsNum();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
            view = initView(viewGroup, position);

        updateData(view, position);

        return view;
    }

    private View initView(ViewGroup viewGroup, int position) {

        View view;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_item, viewGroup, false);
        view.setTag(new ViewHolderItemImage(view));

        return view;
    }

    private void updateData(View view, int position) {
        if (view == null) return;
        Object tag = view.getTag();
        if (!(tag instanceof IViewHolder)) return;

        ((IViewHolder) tag).setData(position);

    }

    private class ViewHolderItemImage implements IViewHolder, View.OnClickListener {

        private final IImageLoaderListener listenerLoader;
        private ImageView ivItem;
        private int position;
        private IThumbnailLoader loader;

        ViewHolderItemImage(View view) {
            ivItem = view.findViewById(R.id.ivItem);
            view.setOnClickListener(this);
            loader = new ThumbnailLoader();
            loader.setListener(listenerLoader = new LoaderListener());
        }

        @Override
        public void setData(int position) {
            this.position = position;

            ivItem.setImageDrawable(null);
            loader.load(ivItem.getContext().getContentResolver(), presenter.getItemId(position));

        }

        @Override
        public void onClick(View v) {
            presenter.onClickImage(position);
        }

        private class LoaderListener implements IImageLoaderListener {
            @Override
            public void onLoad(Bitmap bitmap) {
                ivItem.setImageBitmap(bitmap);
            }
        }
    }

    private class ListenerPresenter implements IPresenterAdapterFolderImagesListener {
        @Override
        public void onUpdate() {
            notifyDataSetChanged();
        }
    }
}
