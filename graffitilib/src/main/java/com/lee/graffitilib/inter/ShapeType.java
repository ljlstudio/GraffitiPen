package com.lee.graffitilib.inter;

import androidx.annotation.IntDef;

/**
 * Author : 李嘉伦
 * e-mail : lijialun@angogo.cn
 * date   : 2021/12/3114:49
 * Package: com.lee.graffitilib.inter
 * desc   :
 */
@IntDef(flag = true, value = {
        ShapeType.BRUSH,
        ShapeType.BITMAP,
        ShapeType.STROKE_BRUSH
})
public @interface ShapeType {
    int BRUSH = 1;
    int BITMAP = 2;
    int STROKE_BRUSH=3;
}
