package org.byters.gallery.view.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class FrameLayoutSquare extends FrameLayout {
    public FrameLayoutSquare(Context context) {
        super(context);
    }

    public FrameLayoutSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayoutSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
