package com.jintin.clerk.app.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jintin.clerk.app.R

class CloseView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_close, this)
    }

    fun show(isShow: Boolean) {
        visibility = View.VISIBLE
        val anim = ObjectAnimator.ofFloat(this, "alpha", if (isShow) 0.5f else 0f)
        anim.duration = 300 // duration 3 seconds
        anim.start()
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                visibility = if (isShow) View.VISIBLE else View.GONE
            }
        })
    }
}