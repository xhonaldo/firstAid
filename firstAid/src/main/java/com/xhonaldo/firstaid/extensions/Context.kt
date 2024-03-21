package com.xhonaldo.firstaid.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * Check if the Internet connectivity is available.
 * @return true if internet connectivity is available, false otherwise.
 */
fun Context.isInternetAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

/**
 * Retrieves color resource with compatibility for older versions.
 * @param colorId The resource id of the color.
 * @return The color value.
 */
fun Context.getCompatColor(@ColorRes colorId: Int) = ResourcesCompat.getColor(resources, colorId, null)

/**
 * Retrieves drawable resource with compatibility for older versions.
 * @param drawableId The resource id of the drawable.
 * @return The drawable.
 */
fun Context.getCompatDrawable(@DrawableRes drawableId: Int) = AppCompatResources.getDrawable(this, drawableId)!!

/**
 * Copies text to clipboard.
 * @param content The text to copy to clipboard.
 */
fun Context.copyToClipboard(content: String) {
    val clipboardManager = ContextCompat.getSystemService(this, ClipboardManager::class.java)!!
    val clip = ClipData.newPlainText("clipboard", content)
    clipboardManager.setPrimaryClip(clip)
}

/**
 * Displays an alert dialog.
 * @param positiveButtonLabel The label for the positive button.
 * @param title The title of the dialog (optional).
 * @param message The message of the dialog (optional).
 * @param cancelable Whether the dialog is cancelable (default is false).
 * @param actionOnPositiveButton The action to perform when the positive button is clicked.
 */
fun Context.showAlertDialog(
    positiveButtonLabel: String,
    title: String = "",
    message: String = "",
    cancelable: Boolean = false,
    actionOnPositiveButton: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
    title.ifNotEmpty { builder.setTitle(it) }
    message.ifNotEmpty { builder.setMessage(it) }
    builder.setCancelable(cancelable)
    builder.setPositiveButton(positiveButtonLabel) { dialog, _ ->
        dialog.cancel()
        actionOnPositiveButton()
    }

    val alert = builder.create()
    alert?.show()
}

/**
 * Checks if the activity is finishing.
 * @return true if the activity is finishing, false otherwise.
 */
fun Context.isActivityFinishing(): Boolean {
    return this is Activity && isFinishing
}

/**
 * Checks if the activity is destroyed.
 * @return true if the activity is destroyed, false otherwise.
 */
fun Context.isActivityDestroyed(): Boolean {
    return this is Activity && isDestroyed
}

/**
 * Opens a URL in a browser.
 * @param url The URL to open.
 * @param newTask Whether to open the URL in a new task (default is false).
 */
fun Context.browse(url: String, newTask: Boolean = false) {
    val intent = Intent(ACTION_VIEW).apply {
        data = Uri.parse(url)
        if (newTask) addFlags(FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

/**
 * Sends an email.
 * @param email The email address.
 * @param subject The subject of the email (optional).
 * @param text The body of the email (optional).
 * @return true if the email client is available and the email sending is initiated, false otherwise.
 */
fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
    val intent = Intent(ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(EXTRA_EMAIL, arrayOf(email))
        if (subject.isNotBlank()) putExtra(EXTRA_SUBJECT, subject)
        if (text.isNotBlank()) putExtra(EXTRA_TEXT, text)
    }

    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        return true
    }
    return false
}

/**
 * Makes a phone call.
 * @param number The phone number to call.
 */
fun Context.makeCall(number: String) {
    startActivity(Intent(ACTION_CALL, Uri.parse("tel:$number")))
}

/**
 * Sends an SMS.
 * @param number The phone number to send the SMS to.
 * @param text The text of the SMS (optional).
 */
fun Context.sendSms(number: String, text: String = "") {
    val intent = Intent(ACTION_VIEW, Uri.parse("sms:$number"))
    intent.putExtra("sms_body", text)
    startActivity(intent)
}

/**
 * Vibrates the device.
 * @param duration The duration of the vibration in milliseconds.
 */
fun Context.vibrate(duration: Long) {
    val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vib.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vib.vibrate(duration)
    }
}

/**
 * Retrieves the version name of the application.
 * @return The version name.
 */
val Context.versionName: String get() {
    val pInfo = packageManager.getPackageInfo(packageName, 0)
    return pInfo.versionName
}

/**
 * Retrieves the version code of the application.
 * @return The version code.
 */
val Context.versionCode: Long get() {
    val pInfo = packageManager.getPackageInfo(packageName, 0)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) pInfo.longVersionCode
    else pInfo.versionCode.toLong()
}
