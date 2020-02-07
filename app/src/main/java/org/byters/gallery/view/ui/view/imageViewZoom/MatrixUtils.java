package org.byters.gallery.view.ui.view.imageViewZoom;

import android.graphics.Matrix;
import android.graphics.RectF;

class MatrixUtils {
    private float[] outValues;

    public MatrixUtils() {
        outValues = new float[9];
    }

    /**
     * Matrix endMatrix = MatrixUtils.findTransformToAllowedBounds(
     * realImageBounds,
     * imageMatrix,
     * allowedBounds,
     * config.getImageInitialPosition());
     *
     * @param initial
     * @param initialTransform
     * @param allowedBounds
     * @param initialPosition
     * @return
     */
    public static Matrix findTransformToAllowedBounds(
            RectF initial,
            Matrix initialTransform,
            RectF allowedBounds,
            InitialPosition initialPosition) {


        RectF initialBounds = new RectF();
        initialBounds.set(initial);

        Matrix transform = new Matrix();
        transform.set(initialTransform);

        RectF current = new RectF(initial);
        transform.mapRect(current);


        //todo add cropiwa pull request

        //todo only if image smaller than screen
        if (initialPosition == InitialPosition.CENTER_INSIDE) {

            float scale = Math.min(allowedBounds.width() / current.width(),
                    allowedBounds.height() / current.height());

            scale(initialBounds, scale, transform, current);


        } else if (initialPosition == InitialPosition.CENTER_CROP) {

            float scale = Math.max(allowedBounds.width() / current.width(),
                    allowedBounds.height() / current.height());

            scale(initialBounds, scale, transform, current);

        }

        //todo if image smaller than screen or image out of screen edges
        float dX = allowedBounds.width() / 2 - (current.left + current.width() / 2);
        float dY = allowedBounds.height() / 2 - (current.top + current.height() / 2);


        translate(initialBounds, dX, dY, transform, current);

        return transform;
    }

    private static void scale(RectF initial, float scale, Matrix transform, RectF outRect) {
        transform.postScale(scale, scale, outRect.centerX(), outRect.centerY());
        transformInitial(initial, transform, outRect);
    }

    private static void translate(RectF initial, float dx, float dy, Matrix transform, RectF outRect) {
        transform.postTranslate(dx, dy);
        transformInitial(initial, transform, outRect);
    }

    private static void transformInitial(RectF initial, Matrix transform, RectF outRect) {
        outRect.set(initial);
        transform.mapRect(outRect);
    }

    public float getScaleX(Matrix mat) {
        mat.getValues(outValues);
        return outValues[Matrix.MSCALE_X];
    }

    public float getXTranslation(Matrix mat) {
        mat.getValues(outValues);
        return outValues[Matrix.MTRANS_X];
    }

    public float getYTranslation(Matrix mat) {
        mat.getValues(outValues);
        return outValues[Matrix.MTRANS_Y];
    }
}
