package infinity.developers.coreutils.Database.Search

/**
 * Index Manager
 * Handles database indexes for faster queries
 */
object IndexManager {

    /** Create normal index */
    fun createIndex(
        table: String,
        column: String,
        unique: Boolean = false
    ): String {

        val indexName = "idx_${table}_${column}"
        val type = if (unique) "UNIQUE" else ""

        return """
            CREATE $type INDEX IF NOT EXISTS $indexName
            ON $table($column)
        """.trimIndent()
    }

    /** Create multi-column index */
    fun createCompositeIndex(
        table: String,
        columns: List<String>,
        unique: Boolean = false
    ): String {

        val joinedColumns = columns.joinToString(", ")
        val namePart = columns.joinToString("_")

        val indexName = "idx_${table}_${namePart}"
        val type = if (unique) "UNIQUE" else ""

        return """
            CREATE $type INDEX IF NOT EXISTS $indexName
            ON $table($joinedColumns)
        """.trimIndent()
    }

    /** Drop index */
    fun dropIndex(indexName: String): String {
        return "DROP INDEX IF EXISTS $indexName"
    }

    /** Search recommended indexes */
    fun recommendedIndexes(table: String): List<String> {
        return listOf(
            "idx_${table}_id",
            "idx_${table}_name",
            "idx_${table}_created_at"
        )
    }
}