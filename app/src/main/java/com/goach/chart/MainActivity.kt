package com.goach.chart

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        brokenView.setXUnitValue(1)
                .setYUnitValue(2000)
                .setXTextUnits(mutableListOf("0","1","2","3","4","5","6"))
                .setYTextUnits(mutableListOf("2000元","4000元","6000元","8000元","10000元"))
                .setDateList(mutableListOf(Point(1,2999), Point(2,1555), Point(3,10000)
                        , Point(4,6000), Point(5,5098), Point(6,1890)))
                .startDraw()
    }
}
