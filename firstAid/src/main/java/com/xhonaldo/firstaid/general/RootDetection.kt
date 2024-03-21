package com.xhonaldo.firstaid.general

import java.io.File

object RootDetection {

    // Define a property `isDeviceRooted` which checks if the device is rooted
    val isDeviceRooted: Boolean get() = containsRootTags || containsRootPaths || rootProcessChecker

    // Define a property `containsRootTags` to check if the device build tags contain root-related strings
    private val containsRootTags: Boolean get() = android.os.Build.TAGS?.contains("test-keys") == true

    // Define a property `containsRootPaths` to check if any of the root paths exist on the device
    private val containsRootPaths: Boolean get() = rootPaths.any { File(it).exists() }

    // Define a property `rootProcessChecker` to check if the device has a root process running
    private val rootProcessChecker: Boolean  get() = try {
        val process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
        // Check if the process returns a non-empty output indicating a root process
        process.inputStream.bufferedReader().readLine().isNotEmpty()
    } catch (e: Exception) {
        // Return false if an exception occurs, indicating no root process
        false
    }

    // Define an array of root paths to check for root-related files
    private val rootPaths = arrayOf(
        "/system/app/Superuser.apk",
        "/sbin/su",
        "/system/bin/su",
        "/system/xbin/su",
        "/data/local/xbin/su",
        "/data/local/bin/su",
        "/system/sd/xbin/su",
        "/system/bin/failsafe/su",
        "/data/local/su"
    )

}
