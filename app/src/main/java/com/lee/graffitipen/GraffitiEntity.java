package com.lee.graffitipen;

import java.io.Serializable;
import java.util.List;

/**
 * Author : 李嘉伦
 * date   : 2021/9/159:53
 * Package:
 * desc   :
 */
public class GraffitiEntity implements Serializable {
    private int thumbRes;
    private List<Integer> paintRes;
    private int type;//0 默认，1 \虚线 2，图片
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public GraffitiEntity setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    public int getType() {
        return type;
    }

    public GraffitiEntity setType(int type) {
        this.type = type;
        return this;
    }

    public int getThumbRes() {
        return thumbRes;
    }

    public GraffitiEntity setThumbRes(int thumbRes) {
        this.thumbRes = thumbRes;
        return this;
    }

    public List<Integer> getPaintRes() {
        return paintRes;
    }

    public GraffitiEntity setPaintRes(List<Integer> paintRes) {
        this.paintRes = paintRes;
        return this;
    }
}