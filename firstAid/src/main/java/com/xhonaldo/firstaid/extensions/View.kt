package com.xhonaldo.firstaid.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

/**
 * Shows the view by setting its visibility to VISIBLE.
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Hides the view by setting its visibility to INVISIBLE.
 * The view still takes up space in the layout.
 */
fun View.hide() {
    visibility = View.INVISIBLE
}

/**
 * Makes the view gone by setting its visibility to GONE.
 * The view is not visible and does not take up space in the layout.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Checks if the view's visibility is set to VISIBLE.
 */
fun View.isVisible() = visibility == View.VISIBLE

/**
 * Checks if the view's visibility is set to GONE.
 */
fun View.isGone() = visibility == View.GONE

/**
 * Checks if the view's visibility is set to INVISIBLE.
 */
fun View.isInvisible() = visibility == View.INVISIBLE

/**
 * Enables the view by setting isEnabled to true and alpha to 1f.
 */
fun View.enable() {
    isEnabled = true
    alpha = 1f
}

/**
 * Disables the view by setting isEnabled to false and alpha to 0.5f.
 */
fun View.disable() {
    isEnabled = false
    alpha = 0.5f
}

/**
 * Makes the view visible if the provided block returns true and the view is not already visible.
 */
fun View.visiableIf(block: () -> Boolean) {
    if (visibility != View.VISIBLE && block()) {
        visibility = View.VISIBLE
    }
}

/**
 * Makes the view invisible if the provided block returns true and the view is not already invisible.
 */
fun View.invisiableIf(block: () -> Boolean) {
    if (visibility != View.INVISIBLE && block()) {
        visibility = View.INVISIBLE
    }
}

/**
 * Displays a Snackbar with the provided message resource and optional parameters.
 */
fun View.showSnackbar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, action: Snackbar.() -> Unit) {
    val snackBar = Snackbar.make(this, getString(messageRes), length).apply {
        action()
        show()
    }
}

/**
 * Sets an action for the Snackbar with the provided action resource and optional parameters.
 */
fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    setAction(actionRes, listener)
    color?.let { setActionTextColor(color) }
}

/**
 * Retrieves a string resource with the given ID.
 */
fun View.getString(@StringRes id: Int) = resources.getString(id)

/**
 * Takes a screenshot of the view.
 * @param height The height of the screenshot. Defaults to the view's height.
 * @param width The width of the screenshot. Defaults to the view's width.
 * @return The screenshot as a Bitmap.
 */
fun View.takeScreenshot(height: Int = getHeight(), width: Int = getWidth()): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    background?.draw(canvas) ?: run {
        canvas.drawColor(Color.WHITE)
    }

    draw(canvas)
    return bitmap
}

/**
 * Hides the keyboard for the current view.
 * @param context The context in which the keyboard is being hidden.
 */
fun View.hideKeyboard(context: Context) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Checks if an EditText is empty, optionally showing an error message.
 * @param error The error message to be displayed if the EditText is empty. Defaults to an empty string.
 * @return True if the EditText is empty, false otherwise.
 */
fun EditText.isEmpty(error: String = ""): Boolean {
    error.takeIf { it.isNotEmpty() }?.let { setError(error) }
    return text.isEmpty()
}

/**
 * Gets the value of the EditText as a string.
 * If the EditText's text is not null, returns its string representation,
 * otherwise, returns an empty string.
 * @return the value of the EditText as a string
 */
val EditText.value
    get() = text?.toString() ?: ""

/**
 * Checks if all EditText fields in a list are empty.
 * @return True if any of the EditText fields are empty, false otherwise.
 */
fun List<EditText>.areFieldsEmpty(): Boolean {
    forEach { if (it.isEmpty()) return true }
    return false
}

/**
 * Automatically scrolls a RecyclerView through a list of items.
 * @param delayMillis The delay between each scroll in milliseconds. Defaults to 4000.
 * @param scrollBy The number of items to scroll by each time. Defaults to 1.
 * @param list The list of items to scroll through.
 */
fun <T> RecyclerView.autoScroll(delayMillis: Long = 4000, scrollBy: Int = 1, list: List<T>) {
    list.takeIf { it.isNotEmpty() }?.let {
        var scrollCount = 0
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                if (scrollCount < it.size) {
                    scrollCount += scrollBy
                    smoothScrollToPosition(scrollCount)
                }
                handler.postDelayed(this, delayMillis)
                if (scrollCount == it.size) scrollCount = 0
            }
        }
        handler.postDelayed(runnable, delayMillis)
    }
}

/**
 * Converts a Drawable to a Bitmap.
 * If the Drawable is a BitmapDrawable, returns the underlying Bitmap.
 * Otherwise, draws the Drawable onto a new Bitmap and returns it.
 *
 * @return the Bitmap representation of the Drawable
 */
fun Drawable.toBitmap(): Bitmap {
    // If the Drawable is a BitmapDrawable, return the underlying Bitmap
    if (this is BitmapDrawable) return bitmap

    // Otherwise, create a new Bitmap and draw the Drawable onto it
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}
