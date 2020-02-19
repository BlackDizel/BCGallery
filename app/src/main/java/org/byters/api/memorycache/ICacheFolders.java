package org.byters.api.memorycache;

import org.byters.api.memorycache.listener.ICacheFoldersListener;
import org.byters.model.ImageFolderMeta;
import org.byters.model.ItemType;

import java.util.ArrayList;

public interface ICacheFolders {
    int getItemsNum();

    String getFolderId(int position);

    String getItemTitle(int position);

    void setListener(ICacheFoldersListener listener);

    void setData(ArrayList<ImageFolderMeta> result);

    int getItemThumbnailId(int position);

    ItemType getItemType(int position);
}
