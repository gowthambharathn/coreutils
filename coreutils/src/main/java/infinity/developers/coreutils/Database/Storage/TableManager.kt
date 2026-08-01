package infinity.developers.coreutils.Database.Storage

/**
 * Table Manager
 * Handles table creation, migration, indexes
 */
object TableManager {

    /** Create standard table */
    fun createTable(
        tableName: String,
        columns: List<String>
    ): String {

        val columnSql = columns.joinToString(", ")

        return """
            CREATE TABLE IF NOT EXISTS $tableName (
                $columnSql
            )
        """.trimIndent()
    }

    /** Drop table */
    fun dropTable(
        tableName: String
    ): String {
        return "DROP TABLE IF EXISTS $tableName"
    }

    /** Rename table */
    fun renameTable(
        oldName: String,
        newName: String
    ): String {
        return """
            ALTER TABLE $oldName
            RENAME TO $newName
        """.trimIndent()
    }

    /** Add new column */
    fun addColumn(
        tableName: String,
        columnDefinition: String
    ): String {
        return """
            ALTER TABLE $tableName
            ADD COLUMN $columnDefinition
        """.trimIndent()
    }

    /** Create common audit table */
    fun createAuditTable(
        tableName: String
    ): String {
        return createTable(
            tableName,
            listOf(
                "id INTEGER PRIMARY KEY AUTOINCREMENT",
                "action TEXT",
                "user_id TEXT",
                "created_at INTEGER"
            )
        )
    }

    /** Check table exists query */
    fun existsQuery(
        tableName: String
    ): String {
        return """
            SELECT name FROM sqlite_master
            WHERE type='table'
            AND name='$tableName'
        """.trimIndent()
    }
}