package infinity.developers.coreutils.Database.Utils

import android.util.Log

/**
 * Logger
 * Professional logging utility
 */
object Logger {

    private const val DEFAULT_TAG = "SecureDB"

    var isEnabled: Boolean = true

    /** Debug Log */
    fun d(
        message: String,
        tag: String = DEFAULT_TAG
    ) {
        if (isEnabled) {
            Log.d(tag, message)
        }
    }

    /** Info Log */
    fun i(
        message: String,
        tag: String = DEFAULT_TAG
    ) {
        if (isEnabled) {
            Log.i(tag, message)
        }
    }

    /** Warning Log */
    fun w(
        message: String,
        tag: String = DEFAULT_TAG
    ) {
        if (isEnabled) {
            Log.w(tag, message)
        }
    }

    /** Error Log */
    fun e(
        message: String,
        tag: String = DEFAULT_TAG
    ) {
        if (isEnabled) {
            Log.e(tag, message)
        }
    }

    /** Error with Exception */
    fun e(
        message: String,
        throwable: Throwable,
        tag: String = DEFAULT_TAG
    ) {
        if (isEnabled) {
            Log.e(tag, message, throwable)
        }
    }

    /** SQL Log */
    fun sql(
        query: String,
        tag: String = DEFAULT_TAG
    ) {
        if (isEnabled) {
            Log.d(tag, "SQL => $query")
        }
    }

    /** Security Log */
    fun security(
        message: String,
        tag: String = DEFAULT_TAG
    ) {
        if (isEnabled) {
            Log.w(tag, "SECURITY => $message")
        }
    }
}