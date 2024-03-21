package com.xhonaldo.firstaid.extensions

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.io.ByteArrayOutputStream

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
 * Observes a LiveData from a LifecycleOwner and performs an action when it changes.
 * @param liveData The LiveData to observe.
 * @param body The action to perform when the LiveData changes.
 */
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))
