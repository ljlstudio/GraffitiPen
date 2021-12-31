package com.lee.graffitilib;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;

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

    public float getSpace() {
        return space;
    }

    public ShapeBuilder withSpace(float space) {
        this.space = space;
        return this;
    }

    public Bitmap[] getShapeBitmap() {
        return shapeBitmap;
    }

    public ShapeBuilder withShapeBitmap(Bitmap... shapeBitmap) {
        this.shapeBitmap = shapeBitmap;
        return this;
    }

    public ShapeBuilder() {
        // default values
        withShapeType(ShapeType.BRUSH);
        withShapeSize(DEFAULT_SHAPE_SIZE);
        withShapeOpacity(DEFAULT_SHAPE_OPACITY);
        withShapeColor(DEFAULT_SHAPE_COLOR);
    }

    public ShapeBuilder withShapeType(int shapeType) {
        currentShapeType = shapeType;
        return this;
    }

    public int getShapeType() {
        return currentShapeType;
    }

    public ShapeBuilder withShapeSize(float size) {
        currentShapeSize = size;
        return this;
    }

    public float getShapeSize() {
        return currentShapeSize;
    }

    public ShapeBuilder withShapeOpacity(@IntRange(from = 0, to = 255) int opacity) {
        currentShapeOpacity = opacity;
        return this;
    }

    public @IntRange(from = 0, to = 255)
    int getShapeOpacity() {
        return currentShapeOpacity;
    }

    public ShapeBuilder withShapeColor(@ColorInt int color) {
        currentShapeColor = color;
        return this;
    }

    public @ColorInt
    int getShapeColor() {
        return currentShapeColor;
    }
}
