package com.lee.graffitilib;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;

import com.lee.graffitilib.inter.BrushViewChangeListener;
import com.lee.graffitilib.inter.ShapeType;


public class ShapeBuilder {

    public static final float DEFAULT_SHAPE_SIZE = 25.0f;
    public static final int DEFAULT_SHAPE_OPACITY = 255;
    public static final int DEFAULT_SHAPE_COLOR = Color.BLACK;


    private int currentShapeType;
    private float currentShapeSize;
    @IntRange(from = 0, to = 255)
    private int currentShapeOpacity;
    @ColorInt
    private int currentShapeColor;
    private Bitmap[] shapeBitmap;
    private float space = 100;
    private BrushViewChangeListener viewChangeListener;

    public BrushViewChangeListener getViewChangeListener() {
        return viewChangeListener;
    }



    public float getSpace() {
        return space;
    }



    public Bitmap[] getShapeBitmap() {
        return shapeBitmap;
    }



    public ShapeBuilder() {
        // default values
        withShapeType(ShapeType.BRUSH);
        withShapeSize(DEFAULT_SHAPE_SIZE);
        withShapeOpacity(DEFAULT_SHAPE_OPACITY);
        withShapeColor(DEFAULT_SHAPE_COLOR);
    }


    /**
     * 设置回调监听
     * @param viewChangeListener
     * @return
     */
    public ShapeBuilder widthViewChangeListener(BrushViewChangeListener viewChangeListener) {
        this.viewChangeListener = viewChangeListener;
        return this;
    }

    /**
     * 设置画笔间距
     * @param space
     * @return
     */
    public ShapeBuilder withSpace(float space) {
        this.space = space;
        return this;
    }


    /**
     * 设置图片类型（图片组合）
     * @param shapeBitmap
     * @return
     */
    public ShapeBuilder withShapeBitmap(Bitmap... shapeBitmap) {
        this.shapeBitmap = shapeBitmap;
        return this;
    }

    /**
     * 设置画笔类型
     * @param shapeType
     * @return
     */
    public ShapeBuilder withShapeType(int shapeType) {
        currentShapeType = shapeType;
        return this;
    }

    /**
     * 设置画笔大小
     * @param size
     * @return
     */
    public ShapeBuilder withShapeSize(float size) {
        currentShapeSize = size;
        return this;
    }

    /**
     * 设置画笔透明度
     * @param opacity
     * @return
     */
    public ShapeBuilder withShapeOpacity(@IntRange(from = 0, to = 255) int opacity) {
        currentShapeOpacity = opacity;
        return this;
    }

    /**
     * 设置画笔颜色
     * @param color
     * @return
     */
    public ShapeBuilder withShapeColor(@ColorInt int color) {
        currentShapeColor = color;
        return this;
    }

    public int getShapeType() {
        return currentShapeType;
    }


    public float getShapeSize() {
        return currentShapeSize;
    }



    public @IntRange(from = 0, to = 255)
    int getShapeOpacity() {
        return currentShapeOpacity;
    }



    public @ColorInt
    int getShapeColor() {
        return currentShapeColor;
    }
}
