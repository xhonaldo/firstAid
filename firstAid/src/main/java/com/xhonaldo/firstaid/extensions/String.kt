package com.xhonaldo.firstaid.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Base64
import android.webkit.URLUtil
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Extension function to provide an empty String.
 */
fun String.Companion.empty() = ""

/**
 * Validates if the string is a valid email address.
 * @param regex
 * @return true if the string is a valid email address, false otherwise.
 */
fun String.isValidEmail(regex: Regex): Boolean = this.isNotEmpty() && regex.matches(this)

/**
 * Validates if the string is a valid URL.
 * @return true if the string is a valid URL, false otherwise.
 */
fun String.isUrl() = isNotEmpty() && URLUtil.isValidUrl(this)


/**p
 * Validates if the string is a valid phone number.
 * @param regex
 * @return true if the string is a valid phone number, false otherwise.
 */
fun String.isPhoneNumber(regex: Regex) = isNotEmpty() && regex.matches(this)

/**
 * Checks if the string contains a digit.
 * @return true if the string contains a digit, false otherwise.
 */
val String.containsDigit: Boolean get() = isNotEmpty() && matches(Regex(".*[0-9].*"))

/**
 * Checks if the string is alphanumeric.
 * @return true if the string is alphanumeric, false otherwise.
 */
val String.isAlphanumeric: Boolean get() = isNotEmpty() && matches(Regex("[a-zA-Z0-9]*"))

/**
 * Converts a Base64 encoded string to a Bitmap.
 * @return The decoded Bitmap.
 */
fun String.base64ToBitmap(): Bitmap {
    val decodedString: ByteArray = Base64.decode(this, Base64.NO_WRAP)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}

/**
 * Executes a block of code if the string is not empty.
 * @param block The block of code to execute if the string is not empty.
 */
fun String.ifNotEmpty(block: (String) -> Unit) {
    if (this.isNotEmpty())
        block(this)
}

/**
 * Converts a hexadecimal color string to RGB values.
 * @return Triple containing the RGB values as strings.
 */
fun String.hextoRGB() : Triple<String, String, String>{
    var name = this
    if (!name.startsWith("#")) name = "#$this"
    val color = Color.parseColor(name)
    val red = Color.red(color)
    val green = Color.green(color)
    val blue = Color.blue(color)

    return Triple(red.toString(), green.toString(), blue.toString())
}

/**
 * Converts a string to a Date object using the specified format.
 * @param withFormat The format of the date string (default is "yyyy/MM/dd hh:mm").
 * @return The parsed Date object.
 */
fun String.toDate(withFormat: String = "yyyy/MM/dd hh:mm"): Date {
    val dateFormat = SimpleDateFormat(withFormat)
    return dateFormat.parse(this) ?: Date()
}

/**
 * Removes all whitespaces from the string.
 * @return the string without any whitespaces
 */
fun String.removeAllWhitespaces(): String {
    return this.replace("\\s+".toRegex(), "")
}

/**
 * Removes duplicate whitespaces from the string.
 * @return the string with duplicate whitespaces replaced by a single whitespace
 */
fun String.removeDuplicateWhitespaces(): String {
    return this.replace("\\s+".toRegex(), " ")
}

