package com.xhonaldo.firstaid.extensions

import android.content.res.Resources
import android.util.TypedValue

/**
 * Extension property to convert an integer value from Density-independent Pixels (DP) to Pixels (PX).
 * @return The integer value converted to pixels.
 */
val Int.DP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Extension property to convert a float value from Density-independent Pixels (DP) to Pixels (PX).
 * @return The float value converted to pixels.
 */
val Float.DP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * Extension property to convert an integer value from Scale-independent Pixels (SP) to Pixels (PX).
 * @return The integer value converted to pixels.
 */
val Int.SP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        toFloat(),
        Resources.getSystem().displayMetrics
    )

/**
 * Extension property to convert a float value from Scale-independent Pixels (SP) to Pixels (PX).
 * @return The float value converted to pixels.
 */
val Float.SP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )
