import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope

/**
 * Launches a coroutine on the main thread.
 * @param work The suspend function to execute.
 */
fun main(work: suspend () -> Unit) =
    CoroutineScope(Dispatchers.Main).launch { work() }

/**
 * Launches a coroutine on the IO thread.
 * @param work The suspend function to execute.
 */
fun io(work: suspend () -> Unit) =
    CoroutineScope(Dispatchers.IO).launch { work() }

/**
 * Launches a coroutine on the default thread.
 * @param work The suspend function to execute.
 */
fun default(work: suspend () -> Unit) =
    CoroutineScope(Dispatchers.Default).launch { work() }

/**
 * Launches a coroutine on an unconfined thread.
 * @param work The suspend function to execute.
 */
fun unconfined(work: suspend () -> Unit) =
    CoroutineScope(Dispatchers.Unconfined).launch { work() }

/**
 * Launches a coroutine on the IO thread and then on the main thread.
 * @param workIO The suspend function to execute on the IO thread.
 * @param workMain The function to execute on the main thread.
 */
fun LifecycleOwner.ioMain(workIO: suspend () -> Unit, workMain: () -> Unit) {
    lifecycleScope.launch(Dispatchers.IO) {
        workIO()
        launch(Dispatchers.Main) { workMain() }
    }
}
