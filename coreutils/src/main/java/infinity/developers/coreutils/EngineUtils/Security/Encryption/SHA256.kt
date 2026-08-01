package infinity.developers.coreutils.EngineUtils.Security.Encryption


/**
 * Created by Gowtham Barath
 * Date: 17-05-2026
 */

import java.security.MessageDigest

/**
 * ============================================================
 * SHA-256 Hashing Class
 * ============================================================
 *
 * Type:
 * One-way hashing algorithm
 *
 * IMPORTANT:
 * SHA-256 is NOT encryption.
 *
 * You CANNOT decrypt SHA-256 hashes.
 *
 * Same input = Same output
 *
 * ============================================================
 *
 * Common Uses:
 * ✔ Password hashing
 * ✔ File integrity checking
 * ✔ Token generation
 * ✔ Data verification
 * ✔ Unique identifiers
 *
 * ============================================================
 */

class SecureSHA256 {

    companion object {

        /**
         * ====================================================
         * Generate SHA-256 Hash
         * ====================================================
         *
         * PARAMETERS:
         * data -> Original plain text
         *
         * RETURNS:
         * SHA-256 hash string
         *
         * HOW IT WORKS:
         * 1. Convert text to bytes
         * 2. Apply SHA-256 algorithm
         * 3. Convert bytes to hexadecimal
         * 4. Return hash string
         *
         * IMPORTANT:
         * Hash cannot be reversed.
         */
        fun hashSHA256(data: String): String {

            // Create SHA-256 digest instance
            val digest = MessageDigest.getInstance("SHA-256")

            // Convert text to hash bytes
            val hashBytes = digest.digest(
                data.toByteArray(Charsets.UTF_8)
            )

            // Convert bytes to hexadecimal string
            return hashBytes.joinToString("") {

                /**
                 * Convert each byte to
                 * 2-digit hexadecimal
                 */
                "%02x".format(it)
            }
        }
    }
}


/**
 * ============================================================
 * USAGE EXAMPLE
 * ============================================================
 */
fun main() {

    /**
     * STEP 1:
     * Original text data
     */
    val text = "Hello World"


    /**
     * STEP 2:
     * Generate SHA-256 hash
     */
    val hashed = SecureSHA256.hashSHA256(text)


    /**
     * STEP 3:
     * Print result
     */
    println("Original:")
    println(text)

    println()

    println("SHA-256 Hash:")
    println(hashed)
}


/**
 * ============================================================
 * SAMPLE OUTPUT
 * ============================================================
 *
 * Original:
 * Hello World
 *
 * SHA-256 Hash:
 * a591a6d40bf420404a011733cfb7b190
 * d62c65bf0bcda32b57b277d9ad9f146e
 *
 * ============================================================
 *
 * IMPORTANT NOTES
 * ============================================================
 *
 * ✔ Hash is always fixed length
 * ✔ Cannot be decrypted
 * ✔ Tiny input change = completely different hash
 * ✔ Secure for integrity checking
 *
 * ============================================================
 */