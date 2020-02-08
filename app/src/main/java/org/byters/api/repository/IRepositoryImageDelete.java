package org.byters.api.repository;

import org.byters.api.repository.listener.IRepositoryImageDeleteListener;

public interface IRepositoryImageDelete {

    void request(String url);

    void setListener(IRepositoryImageDeleteListener listener);
}
