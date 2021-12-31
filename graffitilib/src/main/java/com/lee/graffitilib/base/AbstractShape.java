package com.lee.graffitilib.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.lee.graffitilib.inter.Shape;


abstract public class AbstractShape implements Shape {

    protected float TOUCH_TOLERANCE = 4;

    protected Path path = new Path();
    protected float left, top, right, bottom;

    protected abstract String getTag();

    protected abstract boolean isDrawPath();

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (isDrawPath()) {
            canvas.drawPath(path, paint);
        }
    }

    public RectF getBounds() {
        RectF bounds = new RectF();
        path.computeBounds(bounds, true);
        return bounds;
    }

    public boolean hasBeenTapped() {
        RectF bounds = getBounds();
        return bounds.top < TOUCH_TOLERANCE &&
                bounds.bottom < TOUCH_TOLERANCE &&
                bounds.left < TOUCH_TOLERANCE &&
                bounds.right < TOUCH_TOLERANCE;
    }

    public String toString() {
        return getTag() +
                ": left: " + left +
                " - top: " + top +
                " - right: " + right +
                " - bottom: " + bottom;
    }

}
