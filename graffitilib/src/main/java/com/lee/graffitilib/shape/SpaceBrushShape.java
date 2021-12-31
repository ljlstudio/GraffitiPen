
package com.lee.graffitilib.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;

import com.lee.graffitilib.base.AbstractShape;

public class SpaceBrushShape extends AbstractShape {

    @Override
    protected String getTag() { return "StrokeBrushShape"; }

    @Override
    protected boolean isDrawPath() {
        return false;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
//        canvas.drawPath(path, paint);
        drawGraffiti(canvas,paint);
    }

    @Override
    public void startShape(float x, float y) {
        Log.d(getTag(), "startShape@ " + x + "," + y);
        path.moveTo(x, y);
        left = x;
        top = y;
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
        Log.d(getTag(), "stopShape");
    }


    public void drawGraffiti(Canvas canvas, Paint paint) {
        if (null != path) {
            float[] position = new float[2];
            float[] tan = new float[2];

            PathMeasure pathMeasure = new PathMeasure(path, false);
            float length = pathMeasure.getLength();
            for (int x = 0; x < length; x += 500) {
                pathMeasure.getPosTan(x, position, tan);
                drawPosition(1, canvas, position[0], position[1], paint, path);

            }
        }
    }

    private void drawPosition(int index, Canvas canvas, float x, float y, Paint paint, Path path) {

        canvas.drawPath(path, paint);

    }

}
