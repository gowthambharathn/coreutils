package infinity.developers.coreutils.Database.Storage

/**
 * Entity Mapper
 * Converts database rows <-> Kotlin objects
 */
object EntityMapper {

    /** Convert row to String map */
    fun toStringMap(
        row: Map<String, Any?>
    ): Map<String, String> {

        return row.mapValues {
            it.value?.toString() ?: ""
        }
    }

    /** Get String safely */
    fun getString(
        row: Map<String, Any?>,
        key: String
    ): String {
        return row[key]?.toString() ?: ""
    }

    /** Get Int safely */
    fun getInt(
        row: Map<String, Any?>,
        key: String
    ): Int {
        return when (val value = row[key]) {
            is Int -> value
            is Long -> value.toInt()
            is String -> value.toIntOrNull() ?: 0
            else -> 0
        }
    }

    /** Get Long safely */
    fun getLong(
        row: Map<String, Any?>,
        key: String
    ): Long {
        return when (val value = row[key]) {
            is Long -> value
            is Int -> value.toLong()
            is String -> value.toLongOrNull() ?: 0L
            else -> 0L
        }
    }

    /** Get Double safely */
    fun getDouble(
        row: Map<String, Any?>,
        key: String
    ): Double {
        return when (val value = row[key]) {
            is Double -> value
            is Float -> value.toDouble()
            is String -> value.toDoubleOrNull() ?: 0.0
            else -> 0.0
        }
    }

    /** Get Boolean safely */
    fun getBoolean(
        row: Map<String, Any?>,
        key: String
    ): Boolean {
        return when (val value = row[key]) {
            is Boolean -> value
            is Int -> value == 1
            is Long -> value == 1L
            is String -> value == "1" || value.equals("true", true)
            else -> false
        }
    }

    /** Convert object to row map */
    fun fromPairs(
        vararg pairs: Pair<String, Any?>
    ): Map<String, Any?> {
        return mapOf(*pairs)
    }
}