package infinity.developers.coreutils.Database.Storage

/**
 * Repository Layer
 * Clean bridge between UI/ViewModel and Database
 */
class Repository(
    private val secureDB: infinity.developers.coreutils.Database.Api.SecureDB
) {

    /** Insert row */
    fun insert(
        table: String,
        values: Map<String, Any?>
    ): Long {
        return secureDB.insert(table, values)
    }

    /** Update row */
    fun update(
        table: String,
        values: Map<String, Any?>,
        whereClause: String,
        whereArgs: Array<String>
    ): Int {
        return secureDB.update(
            table,
            values,
            whereClause,
            whereArgs
        )
    }

    /** Delete row */
    fun delete(
        table: String,
        whereClause: String,
        whereArgs: Array<String>
    ): Int {
        return secureDB.delete(
            table,
            whereClause,
            whereArgs
        )
    }

    /** Get all rows */
    fun getAll(
        table: String
    ): List<Map<String, Any?>> {
        return secureDB.query(
            "SELECT * FROM $table"
        )
    }

    /** Find by id */
    fun findById(
        table: String,
        id: Long
    ): Map<String, Any?>? {
        return secureDB.query(
            "SELECT * FROM $table WHERE id = ?",
            arrayOf(id.toString())
        ).firstOrNull()
    }

    /** Custom query */
    fun query(
        sql: String,
        args: Array<String> = emptyArray()
    ): List<Map<String, Any?>> {
        return secureDB.query(sql, args)
    }

    /** Execute raw SQL */
    fun exec(sql: String) {
        secureDB.exec(sql)
    }
}