package com.xhonaldo.firstaid.extensions


/**
 * Checks if the object is null.
 */
fun Any?.isNull(): Boolean {
    return this == null
}

/**
 * Checks if the object is not null.
 */
fun Any?.isNotNull(): Boolean {
    return this != null
}

/**
 * Executes the given [block] if the object is null.
 */
inline fun <T> T?.ifNull(block: () -> Unit) {
    if (isNull())
        block.invoke()
}

/**
 * Executes the given [block] if the object is not null.
 * @return The original object.
 */
inline fun <T> T?.ifNotNull(block: (T) -> Unit) {
    this?.let {
        return block.invoke(it)
    }
}

/**
 * Applies the given [block] to the object if the [condition] is true.
 * @return The original object.
 */
inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T {
    return if (condition) {
        this.apply(block)
    } else {
        this
    }
}