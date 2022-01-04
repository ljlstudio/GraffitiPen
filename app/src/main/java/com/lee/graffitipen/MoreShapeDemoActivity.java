package com.lee.graffitipen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.lee.graffitilib.ShapeBuilder;
import com.lee.graffitilib.inter.BrushViewChangeListener;
import com.lee.graffitilib.inter.ShapeType;
import com.lee.graffitilib.widget.GraffitiDrawingView;
import com.lee.graffitipen.databinding.ActivityMain2Binding;
import com.lee.graffitipen.databinding.ActivityMainBinding;

/**
 *
 */
public class MoreShapeDemoActivity extends AppCompatActivity implements BrushViewChangeListener {

    public static final String TYPE_NAME = "type_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false);
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        int type = intent.getExtras().getInt(TYPE_NAME);

        ShapeBuilder shapeBuilder = new ShapeBuilder()
                .withShapeType(type)
                .withShapeColor(Color.RED)
                .widthViewChangeListener(this)
                .withShapeSize(50);


        binding.graffitiView.setShapeBuilder(shapeBuilder);



        binding.undo.setOnClickListener(v -> {

        });

        binding.reUndo.setOnClickListener(v -> {

        });


    }

    @Override
    public void onViewAdd(GraffitiDrawingView drawingView) {

    }

    @Override
    public void onViewRemoved(GraffitiDrawingView drawingView) {

    }

    @Override
    public void onStartDrawing() {

    }

    @Override
    public void onStopDrawing(float touchX, float touchY, RectF rect) {

    }

    @Override
    public void onTouch(MotionEvent motionEvent) {

    }
}