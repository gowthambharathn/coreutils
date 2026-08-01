package infinity.developers.coreutils.Database.Core

/**
 * Created by Gowtham Barath
 * Date: 26-04-2026
 */

/**
 * Central Database Configuration
 * Change values here for full database control
 */
object DatabaseConfig {

    /** Database Name */
    const val DATABASE_NAME = "secure_database.db"

    /** Database Version */
    const val DATABASE_VERSION = 1

    /** Enable WAL for better performance */
    const val ENABLE_WRITE_AHEAD_LOGGING = true

    /** Foreign Keys Support */
    const val ENABLE_FOREIGN_KEYS = true

    /** SQL Debug Logs */
    const val ENABLE_LOGS = true

    /** Query Timeout (ms) */
    const val QUERY_TIMEOUT = 5000L

    /** Default Text Encoding */
    const val CHARSET = "UTF-8"

    /** Encryption Enabled */
    const val ENABLE_ENCRYPTION = true

    /** Auto Backup */
    const val ENABLE_BACKUP = false

    /** Max DB Size (MB) */
    const val MAX_DATABASE_SIZE_MB = 200

    /** Timestamp Columns */
    const val CREATED_AT = "created_at"
    const val UPDATED_AT = "updated_at"

    /** Common Types */
    const val TYPE_TEXT = "TEXT"
    const val TYPE_INT = "INTEGER"
    const val TYPE_REAL = "REAL"
    const val TYPE_BLOB = "BLOB"

    /** Security Levels */
    const val SECURITY_NORMAL = 0
    const val SECURITY_ENCRYPTED = 1
    const val SECURITY_HIGH = 2
}