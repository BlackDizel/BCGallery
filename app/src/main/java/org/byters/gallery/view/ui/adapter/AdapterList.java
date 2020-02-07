package org.byters.gallery.view.ui.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.byters.api.view.presenter.IPresenterListAdapter;
import org.byters.api.view.presenter.listener.IPresenterListAdapterListener;
import org.byters.api.view.ui.adapter.viewholder.IViewHolder;
import org.byters.api.view.ui.utils.IImageLoader;
import org.byters.api.view.ui.utils.listener.IImageLoaderListener;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;
import org.byters.gallery.view.ui.util.ImageLoader;

public class AdapterList extends BaseAdapter {

    private final ListenerPresenter listenerPresenter;

    public IPresenterListAdapter presenter;

    public AdapterList() {
        GalleryApplication.getInjector().inject(this);
        presenter.setListener(listenerPresenter = new ListenerPresenter());
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
    public int getItemViewType(int position) {
        return presenter.getItemType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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

        if (presenter.isTypeImage(position)) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_item, viewGroup, false);
            view.setTag(new ViewHolderItemImage(view));
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_item_folder, viewGroup, false);
            view.setTag(new ViewHolderItemFolder(view));
        }


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
        private IImageLoader loader;

        ViewHolderItemImage(View view) {
            ivItem = view.findViewById(R.id.ivItem);
            view.setOnClickListener(this);
            loader = new ImageLoader();
            loader.setListener(listenerLoader = new LoaderListener());
        }

        @Override
        public void setData(int position) {
            this.position = position;
            ivItem.setImageDrawable(null);
            loader.load(presenter.getItem(position));
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

    private class ListenerPresenter implements IPresenterListAdapterListener {
        @Override
        public void onUpdate() {
            notifyDataSetChanged();
        }
    }

    private class ViewHolderItemFolder implements IViewHolder, View.OnClickListener {
        private final TextView tvTitle;
        private int position;

        ViewHolderItemFolder(View view) {
            view.setOnClickListener(this);
            tvTitle = view.findViewById(R.id.tvTitle);
        }

        @Override
        public void onClick(View v) {
            presenter.onClickFolder(position);
        }

        @Override
        public void setData(int position) {
            this.position = position;
            tvTitle.setText(presenter.getItemTitle(position));
        }
    }
}
