package org.byters.api.view.ui.utils;

import org.byters.api.view.ui.utils.listener.IImageLoaderListener;

import java.io.File;

public interface IImageLoader {
    void setListener(IImageLoaderListener listener);

    void load(File item);
}
