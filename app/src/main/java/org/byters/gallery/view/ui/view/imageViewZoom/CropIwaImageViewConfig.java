package org.byters.gallery.view.ui.view.imageViewZoom;

import java.util.ArrayList;
import java.util.List;

class CropIwaImageViewConfig {

    static final int SCALE_UNSPECIFIED = -1;
    private static final float DEFAULT_MIN_SCALE = 0.1f;
    private static final float DEFAULT_MAX_SCALE = 10f;
    private float maxScale;
    private float minScale;
    private boolean isScaleEnabled;
    private boolean isTranslationEnabled;
    private float scale;
    private InitialPosition initialPosition;
    private List<ConfigChangeListener> configChangeListeners;

    private CropIwaImageViewConfig() {
        configChangeListeners = new ArrayList<>();
    }

    static CropIwaImageViewConfig createDefault() {
        return new CropIwaImageViewConfig()
                .setMaxScale(DEFAULT_MAX_SCALE)
                .setMinScale(DEFAULT_MIN_SCALE)
                .setImageTranslationEnabled(true)
                .setImageScaleEnabled(true)
                .setImageInitialPosition(InitialPosition.CENTER_INSIDE)
                .setScale(SCALE_UNSPECIFIED);
    }

    float getMaxScale() {
        return maxScale;
    }

    private CropIwaImageViewConfig setMaxScale(float maxScale) {

        this.maxScale = maxScale;
        return this;
    }

    float getMinScale() {
        return minScale;
    }

     CropIwaImageViewConfig setMinScale(float minScale) {
        this.minScale = minScale;
        return this;
    }

    public boolean isImageScaleEnabled() {
        return isScaleEnabled;
    }

    public CropIwaImageViewConfig setImageScaleEnabled(boolean scaleEnabled) {
        this.isScaleEnabled = scaleEnabled;
        return this;
    }

    public boolean isImageTranslationEnabled() {
        return isTranslationEnabled;
    }

    public CropIwaImageViewConfig setImageTranslationEnabled(boolean imageTranslationEnabled) {
        this.isTranslationEnabled = imageTranslationEnabled;
        return this;
    }

    public InitialPosition getImageInitialPosition() {
        return initialPosition;
    }

    public CropIwaImageViewConfig setImageInitialPosition(InitialPosition initialPosition) {
        this.initialPosition = initialPosition;
        return this;
    }

    public float getScale() {
        return scale;
    }

    public CropIwaImageViewConfig setScale(float scale) {
        this.scale = scale;
        return this;
    }

    public void addConfigChangeListener(ConfigChangeListener configChangeListener) {
        if (configChangeListener != null) {
            configChangeListeners.add(configChangeListener);
        }
    }

    public void removeConfigChangeListener(ConfigChangeListener configChangeListener) {
        configChangeListeners.remove(configChangeListener);
    }

    public void apply() {
        for (ConfigChangeListener listener : configChangeListeners) {
            listener.onConfigChanged();
        }
    }
}
