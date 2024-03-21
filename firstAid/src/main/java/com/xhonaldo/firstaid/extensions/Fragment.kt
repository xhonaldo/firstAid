import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Executes a series of fragment transaction operations in a single transaction.
 * @param func The lambda function to perform fragment transaction operations.
 * @return The result of the transaction commit.
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

/**
 * Executes a block of code with the activity context if it's available.
 * @param block The block of code to execute with the activity context.
 */
inline fun Fragment.supplyContext(block: Activity.() -> Unit) {
    activity?.run { block(this) }
}

/**
 * Finishes the hosting activity of the fragment if available.
 */
fun Fragment.finish() {
    supplyContext { finish() }
}

/**
 * Lazily retrieves a value from fragment arguments with the specified label.
 * @param label The label/key to retrieve the value from arguments.
 * @param defaultValue The default value to return if the label doesn't exist or if the retrieved value is not of type T.
 * @return Lazy property to retrieve the value.
 */
inline fun <reified T : Any> Fragment.getValue(label: String, defaultValue: T? = null) = lazy {
    val value = arguments?.get(label)
    if (value is T) value else defaultValue
}

/**
 * Lazily retrieves a non-null value from fragment arguments with the specified label.
 * @param label The label/key to retrieve the value from arguments.
 * @param defaultValue The default value to return if the label doesn't exist or if the retrieved value is not of type T.
 * @return Lazy property to retrieve the non-null value.
 * @throws IllegalArgumentException if the retrieved value is null or not of type T.
 */
inline fun <reified T : Any> Fragment.getValueNonNull(label: String, defaultValue: T? = null) = lazy {
    val value = arguments?.get(label)
    requireNotNull(if (value is T) value else defaultValue) { label }
}