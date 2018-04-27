package com.goach.widget

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Goach All Rights Reserved
 *User: Goach
 *Email: goach0728@gmail.com
 *Des:折线图
 * 第一步绘制坐标隔线，是否显示横轴和纵轴坐标线，坐标隔线颜色，粗细
 * 绘制坐标标识，字体大小，颜色
 * 绘制实心部分，实心颜色，是否显示实心部分
 * 绘制圆点，圆点大小，圆点颜色，是否空心，是否显示圆点
 * 绘制折线，折线粗细，折线颜色
 * 使用方法：
 *     选择传入X,Y轴单位量（默认x为1 ，y为2000）
 *     选择传入X轴和Y轴的坐标标示（默认如图）
 *     传入标注点
 */
class BrokenLineView : View {
    private val defaultBorderColor = Color.BLACK
    private val defaultBorderWidth = 2
    private val defaultIsShowXBorder = true
    private val defaultIsShowYBorder = false
    private val defaultTextSize = 40
    private val defaultTextColor = Color.GRAY
    private val defaultTextPadding = 35
    private val defaultSolidColor = Color.parseColor("#33000000")
    private val defaultIsShowSolid = true
    private val defaultCircleRadius = 10
    private val defaultCircleColor = Color.RED
    private val defaultIsCircleSolid = true
    private val defaultIsShowCircle = true
    private val defaultBrokenWidth = 5
    private val defaultBrokenColor = Color.BLACK
    private val defaultYUnitValue = 2000
    private val defaultXUnitValue = 1
    private var mXBorderColor = defaultBorderColor
    private var mXBorderWidth = defaultBorderWidth
    private var mYBorderColor = defaultBorderColor
    private var mYBorderWidth = defaultBorderWidth
    private var mIsShowXBorder = defaultIsShowXBorder
    private var mIsShowYBorder = defaultIsShowYBorder
    private var mXTextSize = defaultTextSize
    private var mXTextColor = defaultTextColor
    private var mXTextPadding = defaultTextPadding
    private var mYTextSize = defaultTextSize
    private var mYTextColor = defaultTextColor
    private var mYTextPadding = defaultTextPadding
    private var mSolidColor = defaultSolidColor
    private var mIsShowSolid = defaultIsShowSolid
    private var mCircleRadius = defaultCircleRadius
    private var mCircleColor = defaultCircleColor
    private var mIsCircleSolid = defaultIsCircleSolid
    private var mIsShowCircle = defaultIsShowCircle
    private var mBrokenWidth = defaultBrokenWidth
    private var mBrokenColor = defaultBrokenColor
    private var mYUnitValue = defaultYUnitValue
    private var mXUnitValue = defaultXUnitValue

    private lateinit var mXBorderLinePaint:Paint
    private lateinit var mYBorderLinePaint:Paint
    private lateinit var mXTextPaint:TextPaint
    private lateinit var mYTextPaint:TextPaint
    private lateinit var mSolidPaint:Paint
    private lateinit var mCirclePaint:Paint
    private lateinit var mBrokenLinePaint:Paint

    private var mXTextUnits:MutableList<String> = mutableListOf("0","1月","2月","3月","4月","5月","6月")
    private var mYTextUnits:MutableList<String> = mutableListOf("2000","4000","6000","8000","10000")
    private var mDataLists:MutableList<Point> = mutableListOf()

    private var mWidth:Int = 0
    private var mHeight:Int = 0
    private var mXTextHeight = 0f
    private var mYTextHeight = 0f
    //x轴一大格的宽度
    private var itemXWidth = 0f
    //y轴一大格的宽度
    private var itemYWidth = 0f
    //y轴的偏移量
    private var xPadding = 0f
    //x轴的偏移量
    private var yPadding = 0f
    //y轴字体最大宽度
    private var yMaxWidth = 0f
    private lateinit var mSolidPath:Path
    private lateinit var mBrokenPath:Path


    constructor(ctx: Context):this(ctx,null)
    constructor(ctx: Context,attributeSet: AttributeSet?=null):this(ctx,attributeSet,0)
    constructor(ctx: Context,attributeSet: AttributeSet?=null,defaultStyle:Int)
            :super(ctx,attributeSet,defaultStyle){
        initStyle(ctx,attributeSet)
        initPaint()
        initPath()
    }
    private fun initStyle(ctx:Context,attributeSet: AttributeSet?){
        val typeArray = ctx.obtainStyledAttributes(attributeSet,R.styleable.BrokenLineView)
        mXBorderColor = typeArray.getColor(R.styleable.BrokenLineView_XBorderLineColor,defaultBorderColor)
        mXBorderWidth = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_XBorderLineWidth,defaultBorderWidth)
        mYBorderColor = typeArray.getColor(R.styleable.BrokenLineView_YBorderLineColor,defaultBorderColor)
        mYBorderWidth = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_YBorderLineWidth,defaultBorderWidth)
        mIsShowXBorder = typeArray.getBoolean(R.styleable.BrokenLineView_isShowXBorderLine,defaultIsShowXBorder)
        mIsShowYBorder = typeArray.getBoolean(R.styleable.BrokenLineView_isShowYBorderLine,defaultIsShowYBorder)
        mXTextSize = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_XTextSize,defaultTextSize)
        mXTextColor = typeArray.getColor(R.styleable.BrokenLineView_XTextColor,defaultTextColor)
        mXTextPadding = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_XTextPadding,defaultTextPadding)
        mYTextSize = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_YTextSize,defaultTextSize)
        mYTextColor = typeArray.getColor(R.styleable.BrokenLineView_YTextColor,defaultTextColor)
        mYTextPadding = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_YTextPadding,defaultTextPadding)
        mSolidColor = typeArray.getColor(R.styleable.BrokenLineView_solidColor,defaultSolidColor)
        mIsShowSolid = typeArray.getBoolean(R.styleable.BrokenLineView_isShowSolid,defaultIsShowSolid)
        mCircleRadius = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_circleRadius,defaultCircleRadius)
        mCircleColor = typeArray.getColor(R.styleable.BrokenLineView_circleColor,defaultCircleColor)
        mIsCircleSolid = typeArray.getBoolean(R.styleable.BrokenLineView_isCircleSolid,defaultIsCircleSolid)
        mIsShowCircle = typeArray.getBoolean(R.styleable.BrokenLineView_isShowCircle,defaultIsShowCircle)
        mBrokenWidth = typeArray.getDimensionPixelSize(R.styleable.BrokenLineView_BrokenLineWidth,defaultBrokenWidth)
        mBrokenColor = typeArray.getColor(R.styleable.BrokenLineView_BrokenLineColor,defaultBrokenColor)
        mXUnitValue = typeArray.getInteger(R.styleable.BrokenLineView_xUnitValue,defaultXUnitValue)
        mYUnitValue = typeArray.getInteger(R.styleable.BrokenLineView_yUnitValue,defaultYUnitValue)
        typeArray.recycle()
    }
    private fun initPaint(){
        mXBorderLinePaint = createBasePaint()
        mXBorderLinePaint.strokeWidth = mXBorderWidth.toFloat()
        mXBorderLinePaint.color = mXBorderColor

        mYBorderLinePaint = createBasePaint()
        mYBorderLinePaint.strokeWidth = mYBorderWidth.toFloat()
        mYBorderLinePaint.color = mYBorderColor

        mXTextPaint = createTextPaint()
        mXTextPaint.color = mXTextColor
        mXTextPaint.textSize = mXTextSize.toFloat()

        mYTextPaint = createTextPaint()
        mYTextPaint.color = mYTextColor
        mYTextPaint.textSize = mYTextSize.toFloat()

        mSolidPaint = createBasePaint()
        mSolidPaint.color = mSolidColor
        mSolidPaint.style = Paint.Style.FILL

        mCirclePaint = createBasePaint()
        mCirclePaint.strokeWidth = mCircleRadius.toFloat()/4

        mBrokenLinePaint = createBasePaint()
        mBrokenLinePaint.strokeWidth = mBrokenWidth.toFloat()
        mBrokenLinePaint.style = Paint.Style.STROKE
		mBrokenLinePaint.color = mBrokenColor
	mBrokenLinePaint.color =  mBrokenColor   
	

        mXTextHeight = getTextHeight(mXTextPaint)
        mYTextHeight = getTextHeight(mYTextPaint)
    }
    private fun initPath(){
        mSolidPath = Path()
        mBrokenPath = Path()
    }
    private fun createBasePaint():Paint{
        val basePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        basePaint.isAntiAlias = true
        return basePaint
    }
    private fun createTextPaint():TextPaint{
        val textPaint = TextPaint()
        textPaint.isAntiAlias = true
        return textPaint
    }

    fun setXUnitValue(value:Int):BrokenLineView{
        this.mXUnitValue = value
        return this
    }

    fun setYUnitValue(value:Int):BrokenLineView{
        this.mYUnitValue = value
        return this
    }

    fun setXTextUnits(textUnits:List<String>):BrokenLineView{
        mXTextUnits.clear()
        mXTextUnits.addAll(textUnits)
        return this
    }

    fun setYTextUnits(textUnits: List<String>):BrokenLineView{
        mYTextUnits.clear()
        mYTextUnits.addAll(textUnits)
        return this
    }

    fun setDateList(dataList:List<Point>):BrokenLineView{
        mDataLists.clear()
        mDataLists.addAll(dataList)
        return this
    }

    fun startDraw(){
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawXY(canvas)
        drawXText(canvas)
        drawYText(canvas)
        if(mIsShowSolid){
            drawSolid(canvas)
        }
        drawBroken(canvas)
        if(mIsShowCircle){
            drawCircle(canvas)
        }

    }
    private fun drawXY(canvas: Canvas){
        Log.d("zgx","mHeight===$mHeight")
        Log.d("zgx","mWidth===$mWidth")
        yMaxWidth = getYTextUnitMaxWidth()
        xPadding = mYBorderWidth/2 + yMaxWidth + mYTextPadding
        //线/2+文字高度+文字边距
        yPadding = mXBorderWidth/2 + mXTextHeight + mXTextPadding
        //绘制X轴
        canvas.drawLine(xPadding,mHeight.toFloat()-yPadding,
                mWidth.toFloat(),mHeight.toFloat()-yPadding,mXBorderLinePaint)
        //绘制Y轴
        canvas.drawLine(xPadding,mHeight.toFloat()-yPadding,xPadding,0f,mYBorderLinePaint)
    }
    private fun drawXText(canvas: Canvas){
        //尾部多出半份0.5f
        itemXWidth = (mWidth - xPadding) / (mXTextUnits.size -0.5f)
        mXTextUnits.forEachIndexed { index, s ->
            //绘制X轴标注文字,包括起始点,x指的是文字左边,y指的文字baseline
            val xPoint = xPadding + index * itemXWidth
            canvas.drawText(s,xPoint - getTextWidth(mXTextPaint,s)/2,
                    mHeight - mXTextPaint.fontMetrics.descent,mXTextPaint)
            if(mIsShowYBorder){
                //绘制Y方向格子线
                canvas.drawLine(xPoint,mHeight.toFloat()-yPadding,xPoint,0f,mYBorderLinePaint)
            }
        }
    }
    private fun drawYText(canvas: Canvas){
        //尾部多出半份0.5f
        itemYWidth = (mHeight - yPadding) / (mYTextUnits.size + 0.5f)
        mYTextUnits.forEachIndexed { index, s ->
            val yPoint = mHeight - yPadding - (index + 1) * itemYWidth
            //绘制Y轴标注文字,给点偏移量mYTextHeight/4
            canvas.drawText(s,yMaxWidth - getTextWidth(mYTextPaint,s),
                    yPoint + mYTextHeight/4,mYTextPaint)
            if(mIsShowXBorder){
                //绘制X方向格子线
                canvas.drawLine(xPadding,yPoint,mWidth.toFloat(),yPoint,mXBorderLinePaint)
            }
        }
    }
    private fun drawSolid(canvas: Canvas){
        val xLittleUnit = itemXWidth/mXUnitValue
        val yLittleUnit = itemYWidth/mYUnitValue
	mSolidPath.reset()
        mSolidPath.moveTo(xPadding,mHeight - yPadding)
        mDataLists.forEach {
            mSolidPath.lineTo(xPadding + xLittleUnit * it.x , mHeight - yPadding - yLittleUnit * it.y)
        }
        mSolidPath.lineTo(mWidth.toFloat(),mHeight - yPadding)
        mSolidPath.close()
        canvas.drawPath(mSolidPath,mSolidPaint)
    }
    private fun drawBroken(canvas: Canvas){
        val xLittleUnit = itemXWidth/mXUnitValue
        val yLittleUnit = itemYWidth/mYUnitValue
	mBrokenPath.reset()
        mDataLists.forEachIndexed { index, point ->
            if(index==0){
                mBrokenPath.moveTo(xPadding + xLittleUnit * point.x , mHeight - yPadding - yLittleUnit * point.y)
            }else{
                mBrokenPath.lineTo(xPadding + xLittleUnit * point.x , mHeight - yPadding - yLittleUnit * point.y)
            }
        }
        canvas.drawPath(mBrokenPath,mBrokenLinePaint)
    }
    private fun drawCircle(canvas:Canvas){
        val xLittleUnit = itemXWidth/mXUnitValue
        val yLittleUnit = itemYWidth/mYUnitValue
        mDataLists.forEach {
            if(mIsCircleSolid){
                mCirclePaint.style = Paint.Style.FILL
                mCirclePaint.color =  Color.WHITE
                canvas.drawCircle(xPadding + xLittleUnit * it.x,
                        mHeight - yPadding - yLittleUnit * it.y,mCircleRadius.toFloat(),mCirclePaint)
                mCirclePaint.style = Paint.Style.STROKE
                mCirclePaint.color =  mCircleColor
                canvas.drawCircle(xPadding + xLittleUnit * it.x,
                        mHeight - yPadding - yLittleUnit * it.y,mCircleRadius.toFloat(),mCirclePaint)
            }else{
                mCirclePaint.style = Paint.Style.FILL
                mCirclePaint.color =  mCircleColor
                canvas.drawCircle(xPadding + xLittleUnit * it.x,
                        mHeight - yPadding - yLittleUnit * it.y,mCircleRadius.toFloat(),mCirclePaint)
            }
        }
    }
    private fun getTextHeight(textPaint:TextPaint):Float{
        return textPaint.fontMetrics.descent - textPaint.fontMetrics.ascent
    }
    private fun getTextWidth(textPaint: TextPaint,s:String):Float{
        return textPaint.measureText(s)
    }
    private fun getYTextUnitMaxWidth():Float{
        var itemStr = ""
        mYTextUnits.forEach {
            if(it.length>itemStr.length){
                itemStr = it
            }
        }
        return getTextWidth(mYTextPaint,itemStr)
    }
}
