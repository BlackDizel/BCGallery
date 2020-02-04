package org.byters.gallery;

import android.app.Application;

import org.byters.api.IInjector;

public class GalleryApplication extends Application {

    /**
     * todo add action filter to open images and movies
     */

    private static IInjector injector;

    public GalleryApplication() {
        injector = new Injector(this);
    }

    public static IInjector getInjector() {
        return injector;
    }
}
