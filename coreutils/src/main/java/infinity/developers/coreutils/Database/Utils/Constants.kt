package infinity.developers.coreutils.Database.Utils

/**
 * Constants
 * Common database constants
 */
object Constants {

    /** Common Column Names */
    const val ID = "id"
    const val UUID = "uuid"
    const val NAME = "name"
    const val EMAIL = "email"
    const val PHONE = "phone"
    const val PASSWORD = "password"

    /** Audit Columns */
    const val CREATED_AT = "created_at"
    const val UPDATED_AT = "updated_at"
    const val DELETED_AT = "deleted_at"

    /** Common Tables */
    const val TABLE_USERS = "users"
    const val TABLE_ROLES = "roles"
    const val TABLE_LOGS = "logs"
    const val TABLE_SETTINGS = "settings"

    /** SQL Types */
    const val TEXT = "TEXT"
    const val INTEGER = "INTEGER"
    const val REAL = "REAL"
    const val BLOB = "BLOB"

    /** Boolean Values */
    const val TRUE = 1
    const val FALSE = 0

    /** Sort */
    const val ASC = "ASC"
    const val DESC = "DESC"

    /** Security Modes */
    const val NORMAL = 0
    const val ENCRYPTED = 1
    const val HIGH = 2
    const val MILITARY = 3

    /** Error Codes */
    const val ERROR_UNKNOWN = 1000
    const val ERROR_DB_CLOSED = 1001
    const val ERROR_INVALID_QUERY = 1002
    const val ERROR_PERMISSION_DENIED = 1003
    const val ERROR_ENCRYPTION_FAILED = 1004

    /** Default Values */
    const val EMPTY = ""
    const val LIMIT_10 = 10
    const val LIMIT_50 = 50
    const val LIMIT_100 = 100
}