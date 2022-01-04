package com.lee.graffitipen;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author : 李嘉伦
 * desc   :
 */
public class GraffitiGridSpaceItemDecoration extends RecyclerView.ItemDecoration {


    private final String TAG = "ImgStickerGridSpaceItemDecoration";

    private int mSpanCount;//横条目数量
    private int mRowSpacing;//行间距
    private int mColumnSpacing;// 列间距

    /**
     * @param spanCount  列数
     * @param rowSpacing 行间距
     */
    public GraffitiGridSpaceItemDecoration(int spanCount, int rowSpacing) {
        this.mSpanCount = spanCount;
        this.mRowSpacing = rowSpacing;
//        this.mColumnSpacing = columnSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // 获取view 在adapter中的位置。
        RecyclerView.Adapter adapter = parent.getAdapter();
        int itemViewType = adapter.getItemViewType(position);
        int column = position % mSpanCount; // view 所在的列
        final int itemCount = state.getItemCount();
        outRect.bottom = mRowSpacing; // item top


//        if (position > itemCount - mSpanCount && position <= itemCount - 1) {
//            outRect.bottom = mRowSpacing;
//        }
    }

}