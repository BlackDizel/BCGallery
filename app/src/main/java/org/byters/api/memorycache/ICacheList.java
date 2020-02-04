package org.byters.api.memorycache;

import org.byters.api.memorycache.listener.ICacheListListener;

import java.io.File;

public interface ICacheList {
    int getItemsNum();

    void addListener(ICacheListListener listener);

    File getFile();

    void setFile(File file);

    File getItem(int position);
}
