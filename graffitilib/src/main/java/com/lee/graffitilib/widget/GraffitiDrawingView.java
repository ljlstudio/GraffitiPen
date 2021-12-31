package com.lee.graffitilib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.lee.graffitilib.ShapeBuilder;
import com.lee.graffitilib.base.AbstractShape;
import com.lee.graffitilib.base.ShapeAndPaint;
import com.lee.graffitilib.inter.BrushViewChangeListener;
import com.lee.graffitilib.inter.ShapeType;
import com.lee.graffitilib.shape.BitmapShape;
import com.lee.graffitilib.shape.BrushShape;
import com.lee.graffitilib.shape.SpaceBrushShape;

import java.util.Stack;

public class GraffitiDrawingView extends View {

    private final Stack<ShapeAndPaint> drawShapes = new Stack<>();
    private final Stack<ShapeAndPaint> redoShapes = new Stack<>();
    private ShapeAndPaint currentShape;
    private ShapeBuilder currentShapeBuilder;
    private boolean isEnabled=false;
    private BrushViewChangeListener viewChangeListener;
    private int space=2;
    private boolean isErasing = false;
    static final float DEFAULT_ERASER_SIZE = 50.0f;
    private float mBrushEraserSize = DEFAULT_ERASER_SIZE;


    public GraffitiDrawingView(Context context) {
        this(context, null);
    }

    public GraffitiDrawingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraffitiDrawingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupBrushDrawing();
    }


    private Paint createPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        paint.setStrokeWidth(currentShapeBuilder.getShapeSize());
        paint.setAlpha(currentShapeBuilder.getShapeOpacity());
        paint.setColor(currentShapeBuilder.getShapeColor());

        return paint;
    }

    private Paint createStokeBrushPaint() {
        Paint paint = createPaint();
        PathEffect effects = new DashPathEffect(new float[]{currentShapeBuilder.getShapeSize()*space,currentShapeBuilder.getShapeSize()*space},0);
        paint.setPathEffect(effects);
        return paint;
    }
    private Paint createBitmapPaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(100);
        return paint;
    }

    private Paint createEraserPaint() {
        Paint paint = createPaint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        return paint;
    }

    private void setupBrushDrawing() {
        setLayerType(LAYER_TYPE_HARDWARE, null);
        setVisibility(View.GONE);
        currentShapeBuilder = new ShapeBuilder();

        enableDrawing(true);
    }

    public void clearAll() {
        drawShapes.clear();
        redoShapes.clear();
        invalidate();
    }

   public   void setBrushViewChangeListener(BrushViewChangeListener brushViewChangeListener) {
        viewChangeListener = brushViewChangeListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (ShapeAndPaint shape : drawShapes) {
            shape.getShape().draw(canvas, shape.getPaint());
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (isEnabled) {
            float touchX = event.getX();
            float touchY = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onTouchEventDown(touchX, touchY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    onTouchEventMove(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    onTouchEventUp(touchX, touchY);
                    break;
            }
            if(viewChangeListener!=null){
                viewChangeListener.onTouch(event);
            }
            invalidate();
            return true;
        } else {
            return false;
        }
    }

    private void onTouchEventDown(float touchX, float touchY) {
        createShape();
        if (currentShape != null && currentShape.getShape() != null) {
            currentShape.getShape().startShape(touchX, touchY);
        }
    }

    private void onTouchEventMove(float touchX, float touchY) {
        if (currentShape != null && currentShape.getShape() != null) {
            currentShape.getShape().moveShape(touchX, touchY);
        }
    }

    private void onTouchEventUp(float touchX, float touchY) {
        if (currentShape != null && currentShape.getShape() != null) {
            currentShape.getShape().stopShape();
            AbstractShape shape = currentShape.getShape();
            if (shape instanceof BrushShape){
                BrushShape brushShape= (BrushShape) shape;
                endShape(touchX, touchY,brushShape.getBounds());
            }else {
                endShape(touchX, touchY,null);
            }

        }
    }


    private void createShape() {
        final AbstractShape shape;
        Paint paint = createPaint();
        if (isErasing) {
            shape = new BrushShape();
            paint = createEraserPaint();
        }else if (currentShapeBuilder.getShapeType() == ShapeType.BITMAP) {
            Bitmap[] shapeBitmap = currentShapeBuilder.getShapeBitmap();
            if (shapeBitmap == null || shapeBitmap.length <= 0) {
                shape = new BrushShape();
            } else {
                shape = new BitmapShape();
                ((BitmapShape)shape).setBitmapShape(shapeBitmap);
                ((BitmapShape)shape).setSpace(currentShapeBuilder.getSpace());
                paint = createBitmapPaint();
            }
        } else if (currentShapeBuilder.getShapeType() == ShapeType.STROKE_BRUSH) {
            shape = new SpaceBrushShape();
            paint = createStokeBrushPaint();
        } else {
            shape = new BrushShape();
        }
        currentShape = new ShapeAndPaint(shape, paint);
        drawShapes.push(currentShape);

        if (viewChangeListener != null) {
            viewChangeListener.onStartDrawing();
        }
    }


    private void endShape(float touchX, float touchY, RectF rect) {
        if (currentShape.getShape().hasBeenTapped()) {
            // just a tap, this is not a shape, so remove it
            drawShapes.remove(currentShape);
            //handleTap(touchX, touchY);
        }

        if (viewChangeListener != null) {
            viewChangeListener.onStopDrawing(touchX,touchY,rect);
            viewChangeListener.onViewAdd(this);
        }
    }

    /**
     * 撤销
     * @return
     */
    boolean undo() {
        if (!drawShapes.empty()) {
            redoShapes.push(drawShapes.pop());
            invalidate();
        }
        if (viewChangeListener != null) {
            viewChangeListener.onViewRemoved(this);
        }
        return !drawShapes.empty();
    }

    /**
     * 反撤销
     * @return
     */
    boolean redo() {
        if (!redoShapes.empty()) {
            drawShapes.push(redoShapes.pop());
            invalidate();
        }

        if (viewChangeListener != null) {
            viewChangeListener.onViewAdd(this);
        }
        return !redoShapes.empty();
    }

    // region eraser
    void brushEraser() {
        isEnabled = true;
        isErasing = true;
    }

    void setBrushEraserSize(float brushEraserSize) {
        mBrushEraserSize = brushEraserSize;
    }

    float getEraserSize() {
        return mBrushEraserSize;
    }
    // endregion

    // region Setters/Getters
    public void setShapeBuilder(ShapeBuilder shapeBuilder) {
        currentShapeBuilder = shapeBuilder;
    }

    void enableDrawing(boolean brushDrawMode) {
        isEnabled = brushDrawMode;
        isErasing = !brushDrawMode;
        if (brushDrawMode) {
            setVisibility(View.VISIBLE);
        }
    }

    boolean isDrawingEnabled() {
        return isEnabled;
    }

    @VisibleForTesting
    ShapeAndPaint getCurrentShape() {
        return currentShape;
    }

    @VisibleForTesting
    ShapeBuilder getCurrentShapeBuilder() {
        return currentShapeBuilder;
    }

    @VisibleForTesting
    Pair<Stack<ShapeAndPaint>, Stack<ShapeAndPaint>> getDrawingPath() {
        return new Pair<>(drawShapes, redoShapes);
    }
    // endregion

}

