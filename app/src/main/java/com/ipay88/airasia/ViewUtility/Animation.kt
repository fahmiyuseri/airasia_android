package com.ipay88.airasia.ViewUtility

import android.content.res.Resources
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.activity_guest_details.*

class Animation{

companion object {
    fun SlideInFromTopToBottom(view : View,frame : View){
        val animate = TranslateAnimation(
            0f,
            0f,
            -view.getHeight().toFloat(),
            0f

        )
        animate.duration = 500
        animate.fillAfter = true
        animate.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {
                view.setElevation(pxToDp(10).toFloat())
                frame.setElevation(pxToDp(0).toFloat())
            }
        })
        view.startAnimation(animate)

    }
     fun SlideOutFromBottomToTop(view : View,frame : View){
         val animate = TranslateAnimation(
             0f,
             0f,
             0f,
             -view.getHeight().toFloat()
         )
         animate.duration = 500
         animate.fillAfter = true
         animate.setAnimationListener(object: Animation.AnimationListener{
             override fun onAnimationRepeat(animation: Animation?) {
             }

             override fun onAnimationEnd(animation: Animation?) {
                 view.setElevation(pxToDp(0).toFloat())
                 frame.setElevation(pxToDp(10).toFloat())
             }

             override fun onAnimationStart(animation: Animation?) {
             }
         })
         view.startAnimation(animate)

    }
    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

}


}