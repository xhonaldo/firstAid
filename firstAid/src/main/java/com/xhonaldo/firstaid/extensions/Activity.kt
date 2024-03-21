package com.xhonaldo.firstaid.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Start an activity of type [T].
 * @param T The type of the activity to start.
 */
inline fun <reified T : Activity> Activity.startActivity() {
    // Create an Intent to start the activity of type T
    val intent = Intent()
    intent.setClass(this, T::class.java)

    // Start the activity using the created Intent
    startActivity(intent)
}

/**
 * Start an activity of type [T] with provided options.
 * @param T The type of the activity to start.
 * @param options The options to be passed to the activity.
 */
inline fun <reified T : Activity> Activity.startActivity(options: Bundle) {
    // Create an Intent to start the activity of type T
    val intent = Intent()
    intent.setClass(this, T::class.java)

    // Start the activity using the created Intent and provided options
    startActivity(intent, options)
}