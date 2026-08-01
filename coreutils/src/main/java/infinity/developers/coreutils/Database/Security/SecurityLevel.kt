package infinity.developers.coreutils.Database.Security

/**
 * Security Level
 * Controls how data should be stored/protected
 */
enum class SecurityLevel(
    val level: Int,
    val description: String
) {

    NORMAL(
        0,
        "Plain storage for non-sensitive data"
    ),

    ENCRYPTED(
        1,
        "Encrypted storage for private data"
    ),

    HIGH(
        2,
        "Encrypted + Keystore protected"
    ),

    MILITARY(
        3,
        "Max security with strict controls"
    );

    companion object {

        /** Convert int to enum */
        fun from(level: Int): SecurityLevel {
            return values().find {
                it.level == level
            } ?: NORMAL
        }

        /** Is encryption required */
        fun requiresEncryption(
            securityLevel: SecurityLevel
        ): Boolean {
            return securityLevel != NORMAL
        }

        /** Is keystore required */
        fun requiresKeystore(
            securityLevel: SecurityLevel
        ): Boolean {
            return securityLevel == HIGH ||
                    securityLevel == MILITARY
        }
    }
}