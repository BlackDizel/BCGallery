package org.byters.gallery.view.ui.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.byters.api.view.presenter.IPresenterFoldersAdapter;
import org.byters.api.view.presenter.listener.IPresenterListAdapterListener;
import org.byters.api.view.ui.adapter.viewholder.IViewHolder;
import org.byters.api.view.ui.utils.IThumbnailLoader;
import org.byters.api.view.ui.utils.listener.IImageLoaderListener;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.util.ThumbnailLoader;

public class AdapterFolders extends BaseAdapter {

    private final ListenerPresenter listenerPresenter;

    public IPresenterFoldersAdapter presenter;

    public AdapterFolders() {
        GalleryApplication.getInjector().inject(this);
        presenter.setListener(listenerPresenter = new ListenerPresenter());
        presenter.onCreate();
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

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_folder_item, viewGroup, false);
        view.setTag(new ViewHolderItemFolder(view));

        return view;
    }

    private void updateData(View view, int position) {
        if (view == null) return;
        Object tag = view.getTag();
        if (!(tag instanceof IViewHolder)) return;

        ((IViewHolder) tag).setData(position);

    }


    private class ListenerPresenter implements IPresenterListAdapterListener {
        @Override
        public void onUpdate() {
            notifyDataSetChanged();
        }
    }

    private class ViewHolderItemFolder implements IViewHolder, View.OnClickListener {
        private final TextView tvTitle;
        private final ImageView ivItem;
        private final IImageLoaderListener listenerLoader;
        private int position;
        private IThumbnailLoader loader;


        ViewHolderItemFolder(View view) {
            view.setOnClickListener(this);
            tvTitle = view.findViewById(R.id.tvTitle);
            ivItem = view.findViewById(R.id.ivItem);
            loader = new ThumbnailLoader();
            loader.setListener(listenerLoader = new LoaderListener());
        }

        @Override
        public void onClick(View v) {
            presenter.onClickFolder(position);
        }

        @Override
        public void setData(int position) {
            this.position = position;
            tvTitle.setText(presenter.getItemTitle(position));

            ivItem.setImageDrawable(null);
            loader.load(ivItem.getContext().getContentResolver(), presenter.getItemThumbnailId(position), presenter.getItemType(position));
        }

        private class LoaderListener implements IImageLoaderListener {
            @Override
            public void onLoad(Bitmap bitmap) {
                ivItem.setImageBitmap(bitmap);
            }
        }
    }
}
