package com.xhonaldo.firstaid.extensions


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.xhonaldo.firstaid.general.BaseAnimationListener
import com.xhonaldo.firstaid.general.BaseAnimationListenerData

/**
 * Creates a shake animation effect for the view.
 */
fun View.shake(listenerData: BaseAnimationListenerData? = null) {
    val shakeAnimation = TranslateAnimation(-20f,20f,0f,0f).apply {
        repeatCount = 2
        interpolator = LinearInterpolator()
        duration = 50
        repeatMode = Animation.REVERSE
        setAnimationListener(BaseAnimationListener(listenerData))
    }
    this.startAnimation(shakeAnimation)
}

/**
 * Creates a slide-in animation effect from right to left for the view.
 */
fun View.slideInFromRight(listenerData: BaseAnimationListenerData? = null) {
    val rightToLeftAnimation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 1f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.ABSOLUTE, 0f,
        Animation.ABSOLUTE, 0f).apply {
        interpolator = DecelerateInterpolator()
        duration = 400L
        setAnimationListener(BaseAnimationListener(listenerData))
    }
    this.startAnimation(rightToLeftAnimation)
}

/**
 * Creates an animation effect from center to left for the view.
 */
fun View.centerToLeft(listenerData: BaseAnimationListenerData? = null) {
    val rightToLeftAnimation = TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_PARENT, -1f,
        Animation.ABSOLUTE, 0f,
        Animation.ABSOLUTE, 0f).apply {
        interpolator = DecelerateInterpolator()
        duration = 400L
        setAnimationListener(BaseAnimationListener(listenerData))
    }
    this.startAnimation(rightToLeftAnimation)
}

/**
 * Creates a bottom-to-top slide animation effect for the view.
 */
fun View.bottomToTop() {
    val animation = TranslateAnimation(
        Animation.ABSOLUTE, 0f,
        Animation.ABSOLUTE, 0f,
        Animation.RELATIVE_TO_SELF, 0f,
        Animation.RELATIVE_TO_PARENT, -1f).apply {
        interpolator = DecelerateInterpolator()
        duration = 400
    }
    this.startAnimation(animation)
}

/**
 * Creates a top-to-bottom slide animation effect for the view.
 */
fun View.topToBottom(startY: Float = -1f, endY: Float = 0f, time: Long = 400) {
    val animation = TranslateAnimation(
        Animation.ABSOLUTE, 0f,
        Animation.ABSOLUTE, 0f,
        Animation.RELATIVE_TO_PARENT, startY,
        Animation.RELATIVE_TO_SELF, endY).apply {
        interpolator = FastOutSlowInInterpolator()
        duration = time
    }
    this.startAnimation(animation)
}

/**
 * Creates a scale animation effect for the view.
 */
fun View.scaleAnimation() {
    val animation = ScaleAnimation(1f,1.5f,1f,1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f )
    animation.duration = 700
    animation.repeatCount = ValueAnimator.INFINITE
    animation.repeatMode = ValueAnimator.REVERSE
    this@scaleAnimation.startAnimation(animation)
}

/**
 * Creates a slide-from-bottom animation effect for the view.
 */
fun View.slideFromBottom(animationLength: Long = 400, listenerData: BaseAnimationListenerData? = null){
    val animation = TranslateAnimation(
        Animation.ABSOLUTE, 0f,
        Animation.ABSOLUTE, 0f,
        Animation.RELATIVE_TO_SELF, 1f,
        Animation.RELATIVE_TO_SELF, 0f).apply {
        interpolator = DecelerateInterpolator()
        duration = animationLength
    }
    animation.setAnimationListener(BaseAnimationListener(listenerData))
    this.startAnimation(animation)
}

/**
 * Translates the view in or out of the main container.
 */
fun View.translateInOutMainContainer(translateY: Float = 0f, isOutTranslate: Boolean = false) {
    val objectAnimator: ObjectAnimator = if(isOutTranslate)
        ObjectAnimator.ofFloat(this, "translationY", translateY)
    else
        ObjectAnimator.ofFloat(this, "translationY", translateY)

    objectAnimator.apply {
        interpolator = FastOutSlowInInterpolator()
        duration = if(isOutTranslate) 180 else 400
        start()
    }
}

/**
 * Creates a fade-in animation effect for the view.
 */
fun View.fadeIn(
    animationLength: Long = 400,
    delay: Long = 0,
    listenerData:
    BaseAnimationListenerData? = null
) {
    val animation = AlphaAnimation(0f,1f)
    visibility = View.VISIBLE
    animation.startOffset = delay
    animation.duration = animationLength
    animation.setAnimationListener(BaseAnimationListener(listenerData))
    startAnimation(animation)
    visibility = View.VISIBLE
}

/**
 * Creates a fade-out animation effect for the view.
 */
fun View.fadeOut(listenerData: BaseAnimationListenerData? = null) {
    val animation = AlphaAnimation(1f,0f)
    animation.setAnimationListener(BaseAnimationListener(listenerData))
    startAnimation(animation)
}

/**
 * Creates a bounce animation effect for the view.
 */
fun View.bounce(durationParam: Long = 500) {
    ObjectAnimator.ofFloat(this, "translationY", 400f,-50f,0f).apply {
        interpolator = BounceInterpolator()
        duration = durationParam
        start()
    }
}

/**
 * Scales the view from a start scale to an end scale.
 */
fun View.scaleView(startScale: Float, endScale: Float) {
    val anim: Animation = ScaleAnimation(
        startScale, endScale,
        startScale, endScale,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f
    )
    anim.fillAfter = true
    anim.duration = 200
    anim.interpolator = LinearInterpolator()
    startAnimation(anim)
}

/**
 * Scales up the view with a delay and duration.
 */
fun View.scaleUp(delay: Long = 0, setDuration : Long = 200) {
    ObjectAnimator.ofFloat(this, "scaleX", 0f).apply {
        duration = 0
        start()
    }

    ObjectAnimator.ofFloat(this, "scaleY", 0f).apply {
        duration = 0
        start()
    }

    val animScaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f)
        .apply { duration = setDuration }
    val animScaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f)
        .apply { duration = setDuration }

    AnimatorSet().apply {
        play(animScaleX).with(animScaleY)
        startDelay = delay
        start()
    }
}
