package infinity.developers.coreutils.Database.Sql

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * SQL Executor
 * Runs safe SQL commands and queries
 */
object SqlExecutor {

    /** Execute INSERT / UPDATE / DELETE / CREATE */
    fun exec(
        database: SQLiteDatabase,
        sql: String,
        bindArgs: Array<Any?> = emptyArray()
    ) {
        database.execSQL(sql, bindArgs)
    }

    /** Execute SELECT query */
    fun query(
        database: SQLiteDatabase,
        sql: String,
        selectionArgs: Array<String> = emptyArray()
    ): List<Map<String, Any?>> {

        val result = mutableListOf<Map<String, Any?>>()

        val cursor = database.rawQuery(
            sql,
            selectionArgs
        )

        cursor.use {
            while (it.moveToNext()) {
                result.add(readRow(it))
            }
        }

        return result
    }

    /** Execute scalar query */
    fun querySingleValue(
        database: SQLiteDatabase,
        sql: String,
        selectionArgs: Array<String> = emptyArray()
    ): Any? {

        val cursor = database.rawQuery(
            sql,
            selectionArgs
        )

        cursor.use {
            return if (it.moveToFirst()) {
                when (it.getType(0)) {
                    Cursor.FIELD_TYPE_INTEGER -> it.getLong(0)
                    Cursor.FIELD_TYPE_FLOAT -> it.getDouble(0)
                    Cursor.FIELD_TYPE_STRING -> it.getString(0)
                    Cursor.FIELD_TYPE_BLOB -> it.getBlob(0)
                    else -> null
                }
            } else null
        }
    }

    /** Transaction wrapper */
    inline fun transaction(
        database: SQLiteDatabase,
        block: () -> Unit
    ) {
        database.beginTransaction()

        try {
            block()
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }
    }

    private fun readRow(
        cursor: Cursor
    ): Map<String, Any?> {

        val row = mutableMapOf<String, Any?>()

        for (i in 0 until cursor.columnCount) {
            row[cursor.getColumnName(i)] =
                when (cursor.getType(i)) {
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