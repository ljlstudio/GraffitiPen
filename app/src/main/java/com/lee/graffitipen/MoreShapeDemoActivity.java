package com.lee.graffitipen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lee.graffitilib.ShapeBuilder;
import com.lee.graffitilib.inter.BrushViewChangeListener;
import com.lee.graffitilib.inter.ShapeType;
import com.lee.graffitilib.widget.GraffitiDrawingView;
import com.lee.graffitipen.databinding.ActivityMain2Binding;
import com.lee.graffitipen.databinding.ActivityMainBinding;
import com.lee.graffitipen.utils.DisplayUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class MoreShapeDemoActivity extends AppCompatActivity implements BrushViewChangeListener, @Nullable OnItemClickListener {

    public static final String TYPE_NAME = "type_name";
    private ActivityMainBinding binding;
    private GraffitiAdapter graffitiAdapter;
    private ShapeBuilder shapeBuilder;
    private int current=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false);
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        int type = intent.getExtras().getInt(TYPE_NAME);

        shapeBuilder = new ShapeBuilder()
                .withShapeType(type)
                .withShapeColor(Color.RED)
                .widthViewChangeListener(this)
                .withShapeSize(50);


        binding.graffitiView.setShapeBuilder(shapeBuilder);


        binding.undo.setOnClickListener(v -> {
            binding.graffitiView.undo();
        });

        binding.reUndo.setOnClickListener(v -> {
            binding.graffitiView.redo();
        });


        initRecycler();
        initData();


    }

    private void initRecycler() {
        binding.recycler.setLayoutManager(new GridLayoutManager(getApplication(), 3, LinearLayoutManager.VERTICAL, false));
        binding.recycler.addItemDecoration(new GraffitiGridSpaceItemDecoration(3, DisplayUtil.dip2px(this, 8)));
        graffitiAdapter = new GraffitiAdapter(R.layout.graffiti_item_layout,this);
        graffitiAdapter.setOnItemClickListener(this);
        binding.recycler.setAdapter(graffitiAdapter);
    }

    private void initData() {
        List<GraffitiEntity> list = new ArrayList<>();
        List<Integer> defaultList = Arrays.asList(R.drawable.keli);
        GraffitiEntity defaultEntity = new GraffitiEntity().setThumbRes(R.drawable.default_thumb).setPaintRes(defaultList).setType(ShapeType.BRUSH).setSelected(true);


        List<Integer> spaceList = Arrays.asList(R.drawable.keli);
        GraffitiEntity spaceEntity = new GraffitiEntity().setThumbRes(R.drawable.space_thumb).setPaintRes(spaceList).setType(ShapeType.STROKE_BRUSH);

        //爱心
        List<Integer> aixinList = Arrays.asList(
                R.drawable.aixin0,
                R.drawable.aixin1,
                R.drawable.aixin2,
                R.drawable.aixin3,
                R.drawable.aixin4,
                R.drawable.aixin5,
                R.drawable.aixin6,
                R.drawable.aixin7,
                R.drawable.aixin8,
                R.drawable.aixin9,
                R.drawable.aixin10,
                R.drawable.aixin11);


        GraffitiEntity aixinGraffitiEntity = new GraffitiEntity().setThumbRes(R.drawable.aixin_thumb).setPaintRes(aixinList).setType(ShapeType.BITMAP);


        //hpssz_thumb
        List<Integer> hosszList = Arrays.asList(
                R.drawable.hpssz0,
                R.drawable.hpssz1,
                R.drawable.hpssz2,
                R.drawable.hpssz3);
        GraffitiEntity hosszGraffitiEntity = new GraffitiEntity().setThumbRes(R.drawable.hpssz_thumb).setPaintRes(hosszList).setType(ShapeType.BITMAP);


        //qcsvrEntity
        List<Integer> qcsvrList = Arrays.asList(
                R.drawable.qcsvr0,
                R.drawable.qcsvr1,
                R.drawable.qcsvr2,
                R.drawable.qcsvr3);
        GraffitiEntity qcsvrEntity = new GraffitiEntity().setThumbRes(R.drawable.qcsvr_thumb).setPaintRes(qcsvrList).setType(ShapeType.BITMAP);

        list.add(defaultEntity);
        list.add(spaceEntity);
        list.add(aixinGraffitiEntity);
        list.add(hosszGraffitiEntity);
        list.add(qcsvrEntity);

        graffitiAdapter.setNewInstance(list);
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



    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        GraffitiEntity data = (GraffitiEntity) adapter.getData().get(position);
        if (adapter.getData().size() > current) {
            if (current == -1) {
                ((GraffitiEntity)adapter.getData().get(0)).setSelected(false);
            } else {
                ((GraffitiEntity)adapter.getData().get(current)).setSelected(false);
            }
        }
        data.setSelected(true);
        adapter.notifyDataSetChanged();
        current = position;


        switch (data.getType()) {
            case 0:
            default:
                shapeBuilder.withShapeType(ShapeType.BRUSH);
                break;
            case 1:
                shapeBuilder.withShapeType(ShapeType.STROKE_BRUSH);
                break;
            case 2:
                if (data.getPaintRes() != null && data.getPaintRes().size() > 0) {
                    shapeBuilder.withShapeType(ShapeType.BITMAP);
                    Bitmap[] bitmaps = new Bitmap[data.getPaintRes().size()];
                    for (int i = 0; i < data.getPaintRes().size(); i++) {
                        int integer = data.getPaintRes().get(i);
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), integer);
                        bitmaps[i] = bitmap;
                    }
                    shapeBuilder.withShapeBitmap(bitmaps);
                }
                break;
        }
    }
}