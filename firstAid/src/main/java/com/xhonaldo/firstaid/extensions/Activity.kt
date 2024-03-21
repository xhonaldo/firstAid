package com.xhonaldo.firstaid.extensions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat

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
 * Function to check if the activity has a particular permission
 * @param permission The permission to check
 * @return true if the permission is granted, false otherwise
 */
fun Activity.hasPermission(permission: String): Boolean {
    // Use ActivityCompat to check if the permission is granted
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}