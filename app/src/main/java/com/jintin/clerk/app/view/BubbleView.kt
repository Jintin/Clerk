package com.jintin.clerk.app.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jintin.clerk.app.R
import kotlinx.android.synthetic.main.view_bubble.view.*
import kotlin.math.abs


/**
 * Bubble View for Clerk log
 */
class BubbleView : ConstraintLayout, View.OnTouchListener {

    /**
     * Bubble action listener
     */
    interface OnBubbleActionListener {

        /**
         * On drag start
         */
        fun onBubbleDragStart(x: Int, y: Int)

        /**
         * On drag end
         */
        fun onBubbleDragEnd()

        /**
         * On minimize
         */
        fun onBubbleMinimize(minimize: Boolean)

        /**
         * On drag move
         */
        fun onBubbleMove(x: Int, y: Int)

    }

    private var baseCount: Int = -1
    private var newCount: Int = -1
    private var isMinimize = true
    private lateinit var bubbleActionListener: OnBubbleActionListener
    private var startX: Int = 0
    private var startY: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_bubble, this)
        setOnTouchListener(this)
    }

    /**
     * Set bubble action listener
     */
    fun setOnBubbleActionListener(listener: OnBubbleActionListener) {
        bubbleActionListener = listener
        setOnClickListener {
            setMinimize(!isMinimize)
        }

    }

    /**
     * Set alignment of count text
     */
    fun setCountAlignment(moveToStart: Boolean) {
        val para = count.layoutParams as? LayoutParams
        para?.circleAngle = if (moveToStart) 45f else 315f
        count.layoutParams = para
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (!isMinimize) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.rawX.toInt()
                startY = event.rawY.toInt()
                bubbleActionListener.onBubbleDragStart(
                    startX - event.x.toInt(),
                    startY - event.y.toInt()
                )
            }
            MotionEvent.ACTION_MOVE -> bubbleActionListener.onBubbleMove(event.rawX.toInt(), event.rawY.toInt())
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                if (abs(event.rawX.toInt() - startX) + Math.abs(event.rawY.toInt() - startY) > 50) {
                    bubbleActionListener.onBubbleDragEnd()
                    return true
                }
            }
        }
        return false
    }

    /**
     * Set bubble count
     */
    fun setCount(newCount: Int) {
        if (baseCount == -1) {
            baseCount = newCount
        }

        this.newCount = newCount
        if (!isMinimize) {
            baseCount = newCount
        }
        updateCount()
    }

    private fun updateCount() {
        (newCount - baseCount).let {
            if (it < 100) {
                count.text = it.toString()
            } else {
                count.text = context.getString(R.string.max_new_log)
            }
            count.visibility = if (it == 0 || !isMinimize) View.INVISIBLE else View.VISIBLE
        }
    }

    /**
     * Check if minimize or not
     */
    fun isMinimize(): Boolean {
        return isMinimize
    }

    /**
     * Set minimize
     */
    fun setMinimize(minimize: Boolean) {
        isMinimize = minimize
        if (!minimize) {
            baseCount = newCount
            updateCount()
        }
        bubbleActionListener.onBubbleMinimize(isMinimize)
    }

}