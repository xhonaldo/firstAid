package com.xhonaldo.firstaid.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

/**
 * Creates a MutableLiveData instance with an optional initial value.
 * @param initialValue The initial value of the MutableLiveData (default is null).
 * @return The created MutableLiveData instance.
 */
fun <T : Any> mutableLivedataOf(initialValue: T? = null): MutableLiveData<T> {
    return MutableLiveData<T>().apply { value = initialValue }
}

/**
 * Filters the LiveData based on the provided predicate.
 * @param predicate The predicate function to filter LiveData elements.
 * @return A LiveData containing only the elements that satisfy the predicate.
 */
inline fun <T> LiveData<T>.filter(crossinline predicate: (T?) -> Boolean): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this) {
        if (predicate(it)) {
            mediator.value = it
        }
    }
    return mediator
}

/**
 * Takes a specified number of elements from the LiveData.
 * @param count The number of elements to take.
 * @return A LiveData containing the first 'count' elements from the original LiveData.
 */
fun <T> LiveData<T>.take(count: Int): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    var taken = 0
    mediator.addSource(this) {
        if (taken < count) {
            mediator.value = it
            taken++
        } else {
            mediator.removeSource(this)
        }
    }
    return mediator
}

/**
 * Takes elements from the LiveData until the predicate is true.
 * @param predicate The predicate function to determine when to stop taking elements.
 * @return A LiveData containing elements from the original LiveData until the predicate is true.
 */
inline fun <T> LiveData<T>.takeUntil(crossinline predicate: (T?) -> Boolean): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this) {
        if (predicate(it)) {
            mediator.value = it
        } else {
            mediator.removeSource(this)
        }
    }
    return mediator
}

/**
 * Combines two LiveData sources using a combiner function.
 * @param other The other LiveData source to combine with.
 * @param combiner The function to combine elements from both sources.
 * @return A LiveData containing the result of combining elements from both sources.
 */
fun <A, B, Result> LiveData<A>.combineLatest(
    other: LiveData<B>,
    combiner: (A, B) -> Result
): LiveData<Result> {
    val mediator = MediatorLiveData<Result>()
    mediator.addSource(this) { a ->
        val b = other.value
        if (b != null) {
            mediator.value = combiner(a, b)
        }
    }
    mediator.addSource(other) { b ->
        val a = this@combineLatest.value
        if (a != null) {
            mediator.value = combiner(a, b)
        }
    }
    return mediator
}
