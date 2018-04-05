# ChartSimple
通过Canvas绘制一个简单的折线图

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

