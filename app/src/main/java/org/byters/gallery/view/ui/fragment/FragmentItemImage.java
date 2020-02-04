package org.byters.gallery.view.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;

public class FragmentItemImage extends Fragment {

    private static final String EXTRA_IMAGE_URL = "IMAGE_URL";

    public void setArgs(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_IMAGE_URL, url);
        this.setArguments(bundle);
    }
}
