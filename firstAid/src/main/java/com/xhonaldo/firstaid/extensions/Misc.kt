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

/**
 * Extension function to refresh the ArrayList with new items
 * @param items List of items to replace the current contents of the ArrayList
 * @return The modified ArrayList with new items
 */
fun <T:Any> ArrayList<T>.refreshList(items: List<T>): ArrayList<T> {
    // Clear the current list
    this.clear()
    // Add all items from the new list
    this.addAll(items)
    // Return the modified ArrayList
    return this
}

/**
 * Extension function to add only new items to the ArrayList
 * @param items List of items to add to the ArrayList if they do not already exist
 * @return The modified ArrayList with new items added
 */
fun <T:Any> ArrayList<T>.addOnlyNewItems(items: List<T>): ArrayList<T> {
    // Iterate through the items
    items.forEach {
        // Check if the item is not already in the list
        if (!this.contains(it)) {
            // If not, add it to the list
            this.add(it)
        }
    }
    // Return the modified ArrayList
    return this
}

/**
 * Checks if the collection contains any of the elements in the specified [elements].
 * @param elements The elements to check for in the collection.
 * @return true if the collection contains any of the elements, false otherwise.
 */
fun <T> Collection<T>.containsAny(vararg elements: T): Boolean {
    return this.intersect(elements.asIterable().toSet()).isNotEmpty()
}

/**
 * Checks if the array contains any of the elements in the specified [elements].
 * @param elements The elements to check for in the array.
 * @return true if the array contains any of the elements, false otherwise.
 */
fun <T> Array<T>.containsAny(vararg elements: T): Boolean {
    return this.intersect(elements.asIterable().toSet()).isNotEmpty()
}
