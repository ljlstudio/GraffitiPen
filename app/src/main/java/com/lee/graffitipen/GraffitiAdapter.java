package com.lee.graffitipen;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lee.graffitipen.utils.DisplayUtil;


import org.jetbrains.annotations.NotNull;

/**
 * Author : 李嘉伦
 */
public class GraffitiAdapter extends BaseQuickAdapter<GraffitiEntity, BaseViewHolder> {

    private  Context context;
    private  int width;

    public GraffitiAdapter(int layoutResId,Context context) {
        super(layoutResId);
        this.context=context;
        width = (int) ((DisplayUtil.getScreenWidth(context) - (DisplayUtil.dip2px(context, 15) * 2)
                - DisplayUtil.dip2px(context, 8) * 2) / 3f);
    }


    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ViewGroup.LayoutParams layoutParams = holder.getView(R.id.iv).getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = DisplayUtil.dip2px(context, 56);
        holder.getView(R.id.iv).setLayoutParams(layoutParams);


        ViewGroup.LayoutParams layoutParams1 = holder.getView(R.id.selector).getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = DisplayUtil.dip2px(context, 56);
        holder.getView(R.id.selector).setLayoutParams(layoutParams1);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, GraffitiEntity graffitiEntity) {
        ImageView view = holder.getView(R.id.iv);
        view.setImageResource(graffitiEntity.getThumbRes());

        holder.setVisible(R.id.selector, graffitiEntity.isSelected());
    }




}