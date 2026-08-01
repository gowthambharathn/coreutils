package infinity.developers.coreutils.Database.Crypto

import java.security.MessageDigest
import java.security.SecureRandom

/**
 * Hash Engine
 * Used for passwords, tokens, integrity checks
 */
object HashEngine {

    private const val SALT_LENGTH = 16
    private const val ITERATIONS = 10000

    /** Generate Random Salt */
    fun generateSalt(): String {
        val salt = ByteArray(SALT_LENGTH)
        SecureRandom().nextBytes(salt)

        return salt.joinToString("") {
            "%02x".format(it)
        }
    }

    /** Basic SHA-256 Hash */
    fun sha256(text: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(text.toByteArray())

        return bytes.joinToString("") {
            "%02x".format(it)
        }
    }

    /** Salted Hash */
    fun hashWithSalt(
        plainText: String,
        salt: String
    ): String {
        return sha256(plainText + salt)
    }

    /** Multi Round Hash */
    fun hashStrong(
        plainText: String,
        salt: String
    ): String {

        var result = plainText + salt

        repeat(ITERATIONS) {
            result = sha256(result)
        }

        return result
    }

    /** Verify Password */
    fun verify(
        inputText: String,
        salt: String,
        storedHash: String
    ): Boolean {
        return hashStrong(inputText, salt) == storedHash
    }
}