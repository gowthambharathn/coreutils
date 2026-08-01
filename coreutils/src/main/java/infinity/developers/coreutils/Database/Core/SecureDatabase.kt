package infinity.developers.coreutils.Database.Core


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Main Secure Database Engine
 * Handles open, close, CRUD, raw query, transactions
 */
class SecureDatabase(context: Context) :
    SQLiteOpenHelper(
        context,
        DatabaseConfig.DATABASE_NAME,
        null,
        DatabaseConfig.DATABASE_VERSION
    ) {

    private var db: SQLiteDatabase? = null

    override fun onCreate(database: SQLiteDatabase) {
        // Tables can be created here
    }

    override fun onUpgrade(
        database: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Handle migrations here
    }

    fun open() {
        db = writableDatabase

        if (DatabaseConfig.ENABLE_WRITE_AHEAD_LOGGING) {
            db?.enableWriteAheadLogging()
        }

        if (DatabaseConfig.ENABLE_FOREIGN_KEYS) {
            db?.execSQL("PRAGMA foreign_keys=ON")
        }
    }

    override fun close() {
        db?.close()
        db = null
        super.close()
    }

    fun insert(
        table: String,
        values: Map<String, Any?>
    ): Long {
        val contentValues = ContentValues()

        values.forEach { (key, value) ->
            when (value) {
                null -> contentValues.putNull(key)
                is String -> contentValues.put(key, value)
                is Int -> contentValues.put(key, value)
                is Long -> contentValues.put(key, value)
                is Float -> contentValues.put(key, value)
                is Double -> contentValues.put(key, value)
                is Boolean -> contentValues.put(key, if (value) 1 else 0)
                is ByteArray -> contentValues.put(key, value)
                else -> contentValues.put(key, value.toString())
            }
        }

        return writable().insert(table, null, contentValues)
    }

    fun update(
        table: String,
        values: Map<String, Any?>,
        whereClause: String,
        whereArgs: Array<String>
    ): Int {
        val contentValues = ContentValues()

        values.forEach { (key, value) ->
            contentValues.put(key, value?.toString())
        }

        return writable().update(table, contentValues, whereClause, whereArgs)
    }

    fun delete(
        table: String,
        whereClause: String,
        whereArgs: Array<String>
    ): Int {
        return writable().delete(table, whereClause, whereArgs)
    }

    fun query(
        sql: String,
        args: Array<String> = emptyArray()
    ): List<Map<String, Any?>> {

        val result = mutableListOf<Map<String, Any?>>()
        val cursor = readable().rawQuery(sql, args)

        cursor.use {
            while (it.moveToNext()) {
                result.add(readRow(it))
            }
        }

        return result
    }

    fun exec(sql: String) {
        writable().execSQL(sql)
    }

    fun beginTransaction() {
        writable().beginTransaction()
    }

    fun setTransactionSuccessful() {
        writable().setTransactionSuccessful()
    }

    fun endTransaction() {
        writable().endTransaction()
    }

    private fun writable(): SQLiteDatabase {
        return db ?: writableDatabase.also { db = it }
    }

    private fun readable(): SQLiteDatabase {
        return db ?: readableDatabase.also { db = it }
    }

    private fun readRow(cursor: Cursor): Map<String, Any?> {
        val row = mutableMapOf<String, Any?>()

        for (i in 0 until cursor.columnCount) {
            row[cursor.getColumnName(i)] = when (cursor.getType(i)) {
                Cursor.FIELD_TYPE_INTEGER -> cursor.getLong(i)
                Cursor.FIELD_TYPE_FLOAT -> cursor.getDouble(i)
                Cursor.FIELD_TYPE_STRING -> cursor.getString(i)
                Cursor.FIELD_TYPE_BLOB -> cursor.getBlob(i)
                else -> null
            }
        }

        return row
    }
}