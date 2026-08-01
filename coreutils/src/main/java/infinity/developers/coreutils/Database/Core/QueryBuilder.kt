package infinity.developers.coreutils.Database.Core

/**
 * Safe SQL Query Builder
 * Helps build dynamic SQL professionally
 */
class QueryBuilder {

    private var tableName: String = ""
    private val columns = mutableListOf<String>()
    private val whereList = mutableListOf<String>()
    private val whereArgs = mutableListOf<String>()
    private var orderBy: String = ""
    private var limit: Int = -1

    fun table(name: String): QueryBuilder {
        tableName = name
        return this
    }

    fun select(vararg cols: String): QueryBuilder {
        columns.addAll(cols)
        return this
    }

    fun where(condition: String, vararg args: String): QueryBuilder {
        whereList.add(condition)
        whereArgs.addAll(args)
        return this
    }

    fun orderBy(column: String, ascending: Boolean = true): QueryBuilder {
        orderBy = "$column ${if (ascending) "ASC" else "DESC"}"
        return this
    }

    fun limit(count: Int): QueryBuilder {
        limit = count
        return this
    }

    fun build(): Pair<String, Array<String>> {
        val sql = StringBuilder()

        sql.append("SELECT ")

        if (columns.isEmpty()) {
            sql.append("*")
        } else {
            sql.append(columns.joinToString(", "))
        }

        sql.append(" FROM $tableName")

        if (whereList.isNotEmpty()) {
            sql.append(" WHERE ")
            sql.append(whereList.joinToString(" AND "))
        }

        if (orderBy.isNotEmpty()) {
            sql.append(" ORDER BY $orderBy")
        }

        if (limit > 0) {
            sql.append(" LIMIT $limit")
        }

        return Pair(sql.toString(), whereArgs.toTypedArray())
    }

    fun reset() {
        tableName = ""
        columns.clear()
        whereList.clear()
        whereArgs.clear()
        orderBy = ""
        limit = -1
    }
}