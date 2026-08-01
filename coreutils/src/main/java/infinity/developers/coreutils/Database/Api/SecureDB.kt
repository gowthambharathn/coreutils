package infinity.developers.coreutils.Database.Api

import android.content.Context
import android.util.Log
import infinity.developers.coreutils.Database.Core.SecureDatabase

/**
 * Main Public API
 * Professional entry point for all database operations
 */
class SecureDB private constructor(context: Context) {

    private val database = SecureDatabase(context)

    companion object {

        private const val TAG = "SecureDB"

        @Volatile
        private var INSTANCE: SecureDB? = null

        fun init(context: Context): SecureDB {
            Log.d(TAG, "Initializing SecureDB...")

            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SecureDB(context.applicationContext).also {
                    INSTANCE = it
                    Log.d(TAG, "SecureDB initialized successfully")
                }
            }
        }

        fun get(): SecureDB {
            Log.d(TAG, "SecureDB instance requested")

            return INSTANCE
                ?: throw IllegalStateException(
                    "SecureDB not initialized. Call SecureDB.init(context)"
                ).also {
                    Log.e(TAG, "ERROR: SecureDB not initialized")
                }
        }
    }

    /** Open Database */
    fun open() {
        Log.d(TAG, "Opening database connection")
        database.open()
        Log.d(TAG, "Database opened successfully")
    }

    /** Close Database */
    fun close() {
        Log.d(TAG, "Closing database connection")
        database.close()
        Log.d(TAG, "Database closed successfully")
    }

    /** Insert Data */
    fun insert(
        table: String,
        values: Map<String, Any?>
    ): Long {

        Log.d(TAG, "Insert called → Table: $table | Values: $values")

        val result = database.insert(table, values)

        Log.d(TAG, "Insert result → RowID: $result")

        return result
    }

    /** Update Data */
    fun update(
        table: String,
        values: Map<String, Any?>,
        whereClause: String,
        whereArgs: Array<String>
    ): Int {

        Log.d(TAG, "Update → Table: $table | Values: $values | Where: $whereClause")

        val result = database.update(table, values, whereClause, whereArgs)

        Log.d(TAG, "Update result → Rows affected: $result")

        return result
    }

    /** Delete Data */
    fun delete(
        table: String,
        whereClause: String,
        whereArgs: Array<String>
    ): Int {

        Log.d(TAG, "Delete → Table: $table | Where: $whereClause")

        val result = database.delete(table, whereClause, whereArgs)

        Log.d(TAG, "Delete result → Rows deleted: $result")

        return result
    }

    /** Raw Query */
    fun query(
        sql: String,
        args: Array<String> = emptyArray()
    ): List<Map<String, Any?>> {

        Log.d(TAG, "Query executed → SQL: $sql | Args: ${args.joinToString()}")

        val result = database.query(sql, args)

        Log.d(TAG, "Query result size → ${result.size}")

        return result
    }

    /** Execute SQL */
    fun exec(sql: String) {

        Log.d(TAG, "Exec SQL → $sql")

        database.exec(sql)

        Log.d(TAG, "SQL executed successfully")
    }
}