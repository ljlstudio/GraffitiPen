package com.lee.graffitilib.inter;


import android.graphics.RectF;
import android.view.MotionEvent;

import com.lee.graffitilib.widget.GraffitiDrawingView;

public interface BrushViewChangeListener {
    void onViewAdd(GraffitiDrawingView drawingView);

    void onViewRemoved(GraffitiDrawingView drawingView);

    void onStartDrawing();

    void onStopDrawing(float touchX, float touchY, RectF rect);

    void onTouch(MotionEvent motionEvent);
}
