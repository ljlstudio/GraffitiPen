package com.lee.graffitipen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lee.graffitilib.inter.ShapeType;
import com.lee.graffitipen.databinding.ActivityMain2Binding;

public class MainActivity extends AppCompatActivity {
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main2, null, false);
        setContentView(binding.getRoot());


        Intent intent = new Intent(this, MoreShapeDemoActivity.class);

        binding.strokePaint.setOnClickListener(v -> {
            type = ShapeType.STROKE_BRUSH;
            intent.putExtra(MoreShapeDemoActivity.TYPE_NAME, type);
            startActivity(intent);
        });
        binding.bushPaint.setOnClickListener(v -> {
            type = ShapeType.BRUSH;
            intent.putExtra(MoreShapeDemoActivity.TYPE_NAME, type);
            startActivity(intent);
        });

        binding.bitmapPaint.setOnClickListener(v -> {
            type = ShapeType.BITMAP;
            intent.putExtra(MoreShapeDemoActivity.TYPE_NAME, type);
            startActivity(intent);
        });
    }
}