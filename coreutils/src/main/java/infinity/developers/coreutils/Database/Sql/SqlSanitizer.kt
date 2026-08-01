package infinity.developers.coreutils.Database.Sql

/**
 * SQL Sanitizer
 * Prevents injection and validates SQL inputs
 */
object SqlSanitizer {

    private val dangerousKeywords = listOf(
        "DROP ",
        "DELETE ",
        "TRUNCATE ",
        "ALTER ",
        "--",
        ";--",
        "/*",
        "*/",
        "xp_",
        "EXEC "
    )

    /** Validate table / column names */
    fun isSafeIdentifier(name: String): Boolean {
        return name.matches(
            Regex("^[a-zA-Z_][a-zA-Z0-9_]*$")
        )
    }

    /** Throw if identifier unsafe */
    fun requireSafeIdentifier(name: String) {
        require(isSafeIdentifier(name)) {
            "Unsafe SQL identifier: $name"
        }
    }

    /** Check raw SQL text */
    fun isSafeSql(sql: String): Boolean {
        val upper = sql.uppercase()

        return dangerousKeywords.none {
            upper.contains(it)
        }
    }

    /** Throw if SQL unsafe */
    fun requireSafeSql(sql: String) {
        require(isSafeSql(sql)) {
            "Unsafe SQL detected"
        }
    }

    /** Escape LIKE wildcards */
    fun escapeLike(value: String): String {
        return value
            .replace("\\", "\\\\")
            .replace("%", "\\%")
            .replace("_", "\\_")
    }

    /** Clean text input */
    fun cleanInput(text: String): String {
        return text.trim()
            .replace(Regex("\\s+"), " ")
    }
}