package com.xhonaldo.firstaid.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.io.ByteArrayOutputStream
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Executes a block of code if the list is not empty.
 * @param block The block of code to execute if the list is not empty.
 */
fun <T> List<T>.ifNotEmpty(block: (List<T>) -> Unit) {
    if (this.isNotEmpty()) {
        block(this)
    }
}

/**
 * Converts a byte array to a Base64 encoded string.
 * @return The Base64 encoded string representation of the byte array.
 */
fun ByteArray.toBase64(): String = String(this, Charsets.UTF_8)

/**
 * Converts a Bitmap image to a byte array.
 * @param quality The quality of the compression (default is 95).
 * @return The byte array representation of the compressed Bitmap.
 */
fun Bitmap.toByteArray(quality: Int = 95): ByteArray {
    ByteArrayOutputStream().apply {
        compress(Bitmap.CompressFormat.JPEG, quality, this)
        return toByteArray()
    }
}

/**
 * Checks whether the collection is not empty.
 *
 * @return true if the collection is not null and not empty, false otherwise.
 */
fun <T> Collection<T>?.notEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

/**
 * Returns the value corresponding to the given [key], or throws a [NoSuchElementException]
 * if the key is not found in the map.
 * @param key the key to search for in the map
 * @return the value associated with the key
 * @throws NoSuchElementException if the key is not found in the map
 */
fun <K, V> Map<K, V>.getOrThrow(key: K): V {
    return this[key] ?: throw NoSuchElementException("Key $key not found in map")
}

/**
 * Formats the integer into a string using the default number format.
 * @return the formatted string representing the integer
 */
fun Int.toFormattedString(): String {
    return NumberFormat.getInstance().format(this)
}

/**
 * Formats the long integer into a string using the default number format.
 * @return the formatted string representing the long integer
 */
fun Long.toFormattedString(): String {
    return NumberFormat.getInstance().format(this)
}