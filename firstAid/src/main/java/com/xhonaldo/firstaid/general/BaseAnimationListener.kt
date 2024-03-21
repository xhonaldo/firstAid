package com.xhonaldo.firstaid.general

import android.view.animation.Animation

// Define a class `BaseAnimationListener` implementing `Animation.AnimationListener`
// It takes an optional parameter `listenerData` of type `BaseAnimationListenerData`
// which contains optional callbacks for animation events.
class BaseAnimationListener(
    private val listenerData: BaseAnimationListenerData? = null
): Animation.AnimationListener {

    // Implement the onAnimationRepeat method to invoke the `onRepeat` callback from `listenerData`
    override fun onAnimationRepeat(animation: Animation?) {
        listenerData?.onRepeat?.invoke()
    }

    // Implement the onAnimationStart method to invoke the `onStart` callback from `listenerData`
    override fun onAnimationStart(animation: Animation?) {
        listenerData?.onStart?.invoke()
    }

    // Implement the onAnimationEnd method to invoke the `onEnd` callback from `listenerData`
    override fun onAnimationEnd(animation: Animation?) {
        listenerData?.onEnd?.invoke()
    }
}

// Define an extension function `action` on `BaseAnimationListenerData` class
// This function takes a lambda `action` which allows setting the callbacks for animation events
fun action(
    action: BaseAnimationListenerData.() -> Unit
): BaseAnimationListenerData = BaseAnimationListenerData().apply(action)

// Define a data class `BaseAnimationListenerData` to hold callbacks for animation events
data class BaseAnimationListenerData(
    var onRepeat: (() -> Unit)? = null, // Callback for onAnimationRepeat event
    var onStart: (() -> Unit)? = null,  // Callback for onAnimationStart event
    var onEnd: (() -> Unit)? = null      // Callback for onAnimationEnd event
)