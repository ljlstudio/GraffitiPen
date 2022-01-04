# GraffitiPen

## Android 自定义涂鸦画笔，支持Bitmap组合类型

先看效果
![device-2022-01-04-141525](https://user-images.githubusercontent.com/70507884/148017572-1963a282-c0e3-4096-a516-786f660e43a7.gif)

使用步骤：

## 集成
**Step1** 
```
 repositories {
        maven { url 'https://jitpack.io' }
    }
```

**Step2**
```
      implementation 'com.github.ljlstudio:GraffitiPen:tag' ..tag为最新release 版本（1.0.2）
```
## 代码中使用

```
  <com.lee.graffitilib.widget.GraffitiDrawingView
            android:id="@+id/graffiti_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

      shapeBuilder = new ShapeBuilder()
                .withShapeType(type)
                .withShapeColor(Color.RED)
                .widthViewChangeListener(this)
                .withShapeSize(50);
                
      binding.graffitiView.setShapeBuilder(shapeBuilder);

```
