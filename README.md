# ChartSimple
通过Canvas绘制一个简单的折线图

效果图

![折线图.jpg](https://upload-images.jianshu.io/upload_images/2148217-d6d6e1dc3f1a70f1.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 定义的Style

```
  <declare-styleable name="BrokenLineView">
        <attr name="isShowXBorderLine" format="boolean"/>
        <attr name="isShowYBorderLine" format="boolean"/>
        <attr name="XBorderLineColor" format="color"/>
        <attr name="YBorderLineColor" format="color"/>
        <attr name="XBorderLineWidth" format="dimension"/>
        <attr name="YBorderLineWidth" format="dimension"/>
        <attr name="XTextColor" format="color"/>
        <attr name="XTextSize" format="dimension"/>
        <attr name="XTextPadding" format="dimension"/>
        <attr name="YTextColor" format="color"/>
        <attr name="YTextSize" format="dimension"/>
        <attr name="YTextPadding" format="dimension"/>
        <attr name="isShowSolid" format="boolean"/>
        <attr name="solidColor" format="color"/>
        <attr name="circleRadius" format="dimension"/>
        <attr name="circleColor" format="color"/>
        <attr name="isCircleSolid" format="boolean"/>
        <attr name="isShowCircle" format="boolean"/>
        <attr name="BrokenLineWidth" format="dimension"/>
        <attr name="BrokenLineColor" format="color"/>
        <attr name="xUnitValue" format="integer"/>
        <attr name="yUnitValue" format="integer"/>
    </declare-styleable>
    
```

| name        | 类型   |  描述  |
| :--------:   | :-----:   | :----: |
| isShowXBorderLine     | boolean |   是否显示X轴方向的分割线 |
| isShowYBorderLine     | boolean |   是否显示Y轴方向的分割线 |
| XBorderLineColor        | color      |   X轴方向分割线显示颜色    |
| YBorderLineColor        | color      |   Y轴方向分割线显示颜色    |
| XBorderLineWidth        | dimension      |   X轴方向分割线宽    |
| XBorderLineWidth        | dimension      |   Y轴方向分割线宽    |
| XTextColor        | color      |   X轴方向标识符字体颜色    |
| XTextSize        | dimension      |   X轴方向标识符字体大小    |
| XTextPadding        | dimension|   X轴方向标识符字体与X轴间距    |
| YTextColor        | color      |   Y轴方向标识符字体颜色    |
| YTextSize        | dimension      |   Y轴方向标识符字体大小    |
| YTextPadding        | dimension|   Y轴方向标识符字体与Y轴间距    |
| isShowSolid        | boolean      |   是否绘制折线和X轴之间的透明背景    |
| solidColor        | color      |   折线和X轴之间的透明背景颜色    |
| circleRadius        | dimension      |   折线上绘制圆点的半径    |
| circleColor        | color      |   折线上绘制圆点的颜色    |
| isCircleSolid        | boolean      |   折线上绘制圆点是实心还是空心    |
| isShowCircle        | boolean      |   是否绘制折线上的圆点    |
| circleColor        | color      |   折线上绘制圆点的颜色    |
| BrokenLineWidth        | dimension      |   折线线宽度    |
| BrokenLineColor        | color      |   折线线颜色    |
| xUnitValue        | integer |  X轴一个标识的单位量    |
| yUnitValue        | integer |   Y轴一个标识的单位量    |

# 使用

- 依赖

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
  
```
```
 dependencies {
	compile 'com.github.Goach:ChartSimple:1.0.0'
}
```


- 布局

```
<com.goach.widget.BrokenLineView
        android:id="@+id/brokenView"
        android:layout_width="300dp"
        android:layout_height="300dp"/>
```

同时也可以结合上面Style使用


- 使用默认配置

```
brokenView.setDateList(mutableListOf(Point(1,2999), Point(2,1555), Point(3,10000)
                        , Point(4,6000), Point(5,5098), Point(6,1890)))
                .startDraw()
```

- 使用自定义的配置

```
brokenView.setXUnitValue(1)
                .setYUnitValue(2000)
                .setXTextUnits(mutableListOf("0","1","2","3","4","5","6"))
                .setYTextUnits(mutableListOf("2000元","4000元","6000元","8000元","10000元"))
                .setDateList(mutableListOf(Point(1,2999), Point(2,1555), Point(3,10000)
                        , Point(4,6000), Point(5,5098), Point(6,1890)))
                .startDraw()
```

