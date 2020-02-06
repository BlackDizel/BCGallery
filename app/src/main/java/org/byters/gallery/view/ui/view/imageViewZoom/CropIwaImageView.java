package org.byters.gallery.view.ui.view.imageViewZoom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * BASED ON FORK OF CROPIWA PROJECT
 */

public class CropIwaImageView extends ImageView implements ConfigChangeListener, View.OnTouchListener {

    private Matrix imageMatrix;
    private MatrixUtils matrixUtils;
    private GestureProcessor gestureDetector;
    private RectF allowedBounds;
    private RectF imageBounds;
    private RectF realImageBounds;
    private CropIwaImageViewConfig config;

    public CropIwaImageView(Context context) {
        super(context);
        initWith(CropIwaImageViewConfig.createDefault());
    }

    public CropIwaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWith(CropIwaImageViewConfig.createDefault());
    }

    public CropIwaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWith(CropIwaImageViewConfig.createDefault());
    }

    private void initWith(CropIwaImageViewConfig c) {
        config = c;
        config.addConfigChangeListener(this);

        imageBounds = new RectF();
        allowedBounds = new RectF();
        realImageBounds = new RectF();

        matrixUtils = new MatrixUtils();

        imageMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);

        gestureDetector = new GestureProcessor();
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            gestureDetector.onDown(event);
            return true;
        } else gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!hasImageSize()) return;
        placeImageToInitialPosition(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));

    }

    private void placeImageToInitialPosition(int width, int height) {

        //todo send pull request to cropiwa

        updateImageBounds();
        moveImageToTheCenter(width, height);

        allowedBounds.set(0, 0,
                width,
                height);

        switch (config.getImageInitialPosition()) {
            case CENTER_CROP:
                config.setScale(getScaleToCROP(width, height));
                setScalePercent(config.getScale());
                break;
            case CENTER_INSIDE:
                config.setScale(getScaleToINSIDE(width, height));
                setScalePercent(config.getScale());
                break;
        }

    }

    private float getScaleToINSIDE(int viewWidth, int viewHeight) {
        return Math.min(viewWidth / (float) getImageWidth(), viewHeight / (float) getImageHeight());
    }

    private float getScaleToCROP(int viewWidth, int viewHeight) {
        return Math.max(viewWidth / (float) getImageWidth(), viewHeight / (float) getImageHeight());
    }

    private void moveImageToTheCenter(int width, int height) {
        float deltaX = (width / 2f) - imageBounds.centerX();
        float deltaY = (height / 2f) - imageBounds.centerY();
        translateImage(deltaX, deltaY);
    }

    private int getRealImageWidth() {
        Drawable image = getDrawable();
        return image != null ? image.getIntrinsicWidth() : -1;
    }

    private int getRealImageHeight() {
        Drawable image = getDrawable();
        return image != null ? image.getIntrinsicHeight() : -1;
    }

    public int getImageWidth() {
        return (int) imageBounds.width();
    }

    public int getImageHeight() {
        return (int) imageBounds.height();
    }

    public boolean hasImageSize() {
        return getRealImageWidth() != -1 && getRealImageHeight() != -1;
    }

    private void animateToAllowedBounds() {
        updateImageBounds();
        Matrix endMatrix = MatrixUtils.findTransformToAllowedBounds(
                realImageBounds,
                imageMatrix,
                allowedBounds,
                config.getImageInitialPosition());


        MatrixAnimator animator = new MatrixAnimator();
        animator.animate(imageMatrix, endMatrix, new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageMatrix.set((Matrix) animation.getAnimatedValue());
                setImageMatrix(imageMatrix);
                updateImageBounds();
                invalidate();
            }
        });
    }

    private void setScalePercent(float percent) {
        float currentScale = matrixUtils.getScaleX(imageMatrix);
        float factor = percent/ currentScale;
        scaleImage(factor);
        invalidate();
    }

    private void scaleImage(float factor) {
        updateImageBounds();
        scaleImage(factor, imageBounds.centerX(), imageBounds.centerY());
    }

    private void scaleImage(float factor, float pivotX, float pivotY) {
        imageMatrix.postScale(factor, factor, pivotX, pivotY);
        setImageMatrix(imageMatrix);
        updateImageBounds();
    }

    private void translateImage(float deltaX, float deltaY) {
        imageMatrix.postTranslate(deltaX, deltaY);
        setImageMatrix(imageMatrix);
        if (deltaX > 0.01f || deltaY > 0.01f) {
            updateImageBounds();
        }
    }

    private void updateImageBounds() {
        realImageBounds.set(0, 0, getRealImageWidth(), getRealImageHeight());
        imageBounds.set(realImageBounds);
        imageMatrix.mapRect(imageBounds);
    }

    @Override
    public void onConfigChanged() {
        if (Math.abs(getCurrentScalePercent() - config.getScale()) > 0.001f) {
            setScalePercent(config.getScale());
            animateToAllowedBounds();
        }
    }

    private float getCurrentScalePercent() {
        return boundValue(
                0.01f + (matrixUtils.getScaleX(imageMatrix) - config.getMinScale()) / (config.getMaxScale()),
                0.01f, 1f);
    }

    private float boundValue(float value, float lowBound, float highBound) {
        return Math.max(Math.min(value, highBound), lowBound);
    }

    private class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            float newScale = matrixUtils.getScaleX(imageMatrix) * scaleFactor;
            if (isValidScale(newScale)) {
                scaleImage(scaleFactor, detector.getFocusX(), detector.getFocusY());
                config.setScale(getCurrentScalePercent()).apply();
            }
            return true;
        }

        private boolean isValidScale(float newScale) {
            return newScale >= config.getMinScale()
                    && newScale <= (config.getMinScale() + config.getMaxScale());
        }
    }

    private class TranslationGestureListener {

        private float prevX;
        private float prevY;
        private int id;

        private TensionInterpolator interpolator = new TensionInterpolator();

        void onDown(MotionEvent e) {
            onDown(e.getX(), e.getY(), e.getPointerId(0));
        }

        private void onDown(float x, float y, int id) {
            updateImageBounds();
            interpolator.onDown(x, y, imageBounds, allowedBounds);
            saveCoordinates(x, y, id);
        }

        void onTouchEvent(MotionEvent e, boolean canHandle) {
            switch (e.getActionMasked()) {
                case MotionEvent.ACTION_POINTER_UP:
                    onPointerUp(e);
                    return;
                case MotionEvent.ACTION_MOVE:
                    break;
                default:
                    return;
            }

            int index = e.findPointerIndex(id);

            updateImageBounds();

            float currentX = interpolator.interpolateX(e.getX(index));
            float currentY = interpolator.interpolateY(e.getY(index));

            if (canHandle) {
                translateImage(currentX - prevX, currentY - prevY);
            }

            saveCoordinates(currentX, currentY);
        }

        private void onPointerUp(MotionEvent e) {
            //If user lifted finger that we used to calculate translation, we need to find a new one
            if (e.getPointerId(e.getActionIndex()) == id) {
                int index = 0;
                while (index < e.getPointerCount() && index == e.getActionIndex()) {
                    index++;
                }
                onDown(e.getX(index), e.getY(index), e.getPointerId(index));
            }
        }

        private void saveCoordinates(float x, float y) {
            saveCoordinates(x, y, id);
        }

        private void saveCoordinates(float x, float y, int id) {
            this.prevX = x;
            this.prevY = y;
            this.id = id;
        }
    }

    public class GestureProcessor {

        private ScaleGestureDetector scaleDetector;
        private TranslationGestureListener translationGestureListener;

        GestureProcessor() {
            scaleDetector = new ScaleGestureDetector(getContext(), new ScaleGestureListener());
            translationGestureListener = new TranslationGestureListener();
        }

        void onDown(MotionEvent event) {
            translationGestureListener.onDown(event);
        }

        void onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    return;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    animateToAllowedBounds();
                    return;
            }
            if (config.isImageScaleEnabled()) {
                scaleDetector.onTouchEvent(event);
            }

            if (config.isImageTranslationEnabled()) {
                //We don't want image translation while scaling gesture is in progress
                //so - canHandle if scaleDetector.isNotInProgress
                translationGestureListener.onTouchEvent(event, !scaleDetector.isInProgress());
            }
        }
    }

}
