package infinity.developers.coreutils.Database.Search

/**
 * Search Engine
 * Handles dynamic search query creation
 */
object SearchEngine {

    /** Basic keyword search */
    fun keywordSearch(
        table: String,
        column: String,
        keyword: String
    ): Pair<String, Array<String>> {

        val sql = """
            SELECT * FROM $table
            WHERE $column LIKE ?
        """.trimIndent()

        return Pair(sql, arrayOf("%$keyword%"))
    }

    /** Multi-column keyword search */
    fun multiColumnSearch(
        table: String,
        columns: List<String>,
        keyword: String
    ): Pair<String, Array<String>> {

        val conditions = columns.joinToString(" OR ") {
            "$it LIKE ?"
        }

        val args = Array(columns.size) {
            "%$keyword%"
        }

        val sql = """
            SELECT * FROM $table
            WHERE $conditions
        """.trimIndent()

        return Pair(sql, args)
    }

    /** Paginated search */
    fun paginatedSearch(
        table: String,
        keywordColumn: String,
        keyword: String,
        limit: Int,
        offset: Int
    ): Pair<String, Array<String>> {

        val sql = """
            SELECT * FROM $table
            WHERE $keywordColumn LIKE ?
            LIMIT $limit OFFSET $offset
        """.trimIndent()

        return Pair(sql, arrayOf("%$keyword%"))
    }

    /** Ordered search */
    fun orderedSearch(
        table: String,
        keywordColumn: String,
        keyword: String,
        orderBy: String,
        ascending: Boolean = true
    ): Pair<String, Array<String>> {

        val direction = if (ascending) "ASC" else "DESC"

        val sql = """
            SELECT * FROM $table
            WHERE $keywordColumn LIKE ?
            ORDER BY $orderBy $direction
        """.trimIndent()

        return Pair(sql, arrayOf("%$keyword%"))
    }

    /** Exact match search */
    fun exactSearch(
        table: String,
        column: String,
        value: String
    ): Pair<String, Array<String>> {

        val sql = """
            SELECT * FROM $table
            WHERE $column = ?
        """.trimIndent()

        return Pair(sql, arrayOf(value))
    }
}