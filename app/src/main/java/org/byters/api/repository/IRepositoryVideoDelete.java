package org.byters.api.repository;

import org.byters.api.repository.listener.IRepositoryVideoDeleteListener;

public interface IRepositoryVideoDelete {
    void setListener(IRepositoryVideoDeleteListener listener);

    void request(String path);
}
