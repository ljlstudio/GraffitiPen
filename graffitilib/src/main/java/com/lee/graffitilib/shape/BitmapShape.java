package com.lee.graffitilib.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;

import com.lee.graffitilib.base.AbstractShape;

/**
 * Author : 李嘉伦
 * e-mail : lijialun@angogo.cn
 * date   : 2021/9/1414:58
 * Package: ja.burhanrashid52.photoeditor.shape
 * desc   :
 */
public class BitmapShape extends AbstractShape {

    public static final String TAG = BitmapShape.class.getSimpleName();
    private Bitmap[] bitmap;
    private int currentIndex = 0;
    private float space;

    public void setSpace(float space) {
        this.space = space;
    }

    public void setBitmapShape(Bitmap[] shapeBitmap) {
        this.bitmap = shapeBitmap;
    }


    @Override
    protected String getTag() {
        return "BitmapShape";
    }

    @Override
    protected boolean isDrawPath() {
        return false;
    }

    @Override
    public void startShape(float x, float y) {
        path.moveTo(x, y);
        left = x;
        top = y;
//        currentIndex = 0;
    }

    @Override
    public void moveShape(float x, float y) {
        float dx = Math.abs(x - left);
        float dy = Math.abs(y - top);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(left, top, (x + left) / 2, (y + top) / 2);
            left = x;
            top = y;
        }
    }

    @Override
    public void stopShape() {

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        drawGraffiti(canvas, paint);
    }


    public void drawGraffiti(Canvas canvas, Paint paint) {
        if (null != path) {
            float[] position = new float[2];
            float[] tan = new float[2];
            currentIndex = 0;
            PathMeasure pathMeasure = new PathMeasure(path, false);
            float length = pathMeasure.getLength();
            Log.d(TAG, "THE space IS" + space);
            for (int x = 0; x < length; x += space) {
                pathMeasure.getPosTan(x, position, tan);
                drawPosition(1, canvas, position[0], position[1], paint, path);
                if (currentIndex + 1 < bitmap.length) {
                    currentIndex += 1;
                } else {
                    currentIndex = 0;
                }
            }
        }
    }

    private void drawPosition(int index, Canvas canvas, float x, float y, Paint paint, Path path) {

        canvas.drawBitmap(bitmap[currentIndex], x - 0, y - 0, paint);

    }


}