package infinity.developers.coreutils.EngineUtils.Security.Encryption


/**
 * Created by Gowtham Barath
 * Date: 17-05-2026
 */

import android.util.Base64
import java.security.KeyPair
import java.security.KeyPairGenerator
import javax.crypto.Cipher

/**
 * ============================================================
 * RSA Encryption Class
 * ============================================================
 *
 * Type:
 * Public Key Encryption
 *
 * RSA uses 2 keys:
 *
 * 1. Public Key
 *    -> Used for encryption
 *
 * 2. Private Key
 *    -> Used for decryption
 *
 * ============================================================
 *
 * Common Uses:
 * ✔ Secure communication
 * ✔ AES key protection
 * ✔ Digital signatures
 * ✔ Authentication systems
 *
 * ============================================================
 */

class SecureRSA {

    companion object {

        /**
         * RSA encryption mode
         */
        private const val RSA_MODE =
            "RSA/ECB/PKCS1Padding"


        /**
         * ====================================================
         * Generate RSA Public & Private Keys
         * ====================================================
         *
         * RETURNS:
         * RSA key pair
         *
         * IMPORTANT:
         * - Public key can be shared
         * - Private key must remain secret
         */
        fun generateKeyPair(): KeyPair {

            // Create RSA key generator
            val keyPairGenerator =
                KeyPairGenerator.getInstance("RSA")

            // Generate 2048-bit RSA keys
            keyPairGenerator.initialize(2048)

            return keyPairGenerator.generateKeyPair()
        }


        /**
         * ====================================================
         * Encrypt Data Using Public Key
         * ====================================================
         *
         * PARAMETERS:
         * data      -> Original text
         * publicKey -> RSA public key
         *
         * RETURNS:
         * Base64 encrypted string
         *
         * HOW IT WORKS:
         * 1. Initialize RSA cipher
         * 2. Encrypt using public key
         * 3. Convert result to Base64
         */
        fun encryptWithRSA(
            data: String,
            publicKey: java.security.PublicKey
        ): String {

            // Create RSA cipher
            val cipher = Cipher.getInstance(RSA_MODE)

            // Initialize encrypt mode
            cipher.init(
                Cipher.ENCRYPT_MODE,
                publicKey
            )

            // Encrypt text
            val encryptedBytes = cipher.doFinal(
                data.toByteArray(Charsets.UTF_8)
            )

            // Convert encrypted bytes to Base64
            return Base64.encodeToString(
                encryptedBytes,
                Base64.NO_WRAP
            )
        }


        /**
         * ====================================================
         * Decrypt Data Using Private Key
         * ====================================================
         *
         * PARAMETERS:
         * encryptedData -> Base64 encrypted text
         * privateKey    -> RSA private key
         *
         * RETURNS:
         * Original plain text
         *
         * HOW IT WORKS:
         * 1. Decode Base64
         * 2. Initialize RSA cipher
         * 3. Decrypt using private key
         * 4. Return original text
         */
        fun decryptWithRSA(
            encryptedData: String,
            privateKey: java.security.PrivateKey
        ): String {

            // Decode Base64 string
            val encryptedBytes = Base64.decode(
                encryptedData,
                Base64.NO_WRAP
            )

            // Create RSA cipher
            val cipher = Cipher.getInstance(RSA_MODE)

            // Initialize decrypt mode
            cipher.init(
                Cipher.DECRYPT_MODE,
                privateKey
            )

            // Decrypt bytes
            val decryptedBytes = cipher.doFinal(
                encryptedBytes
            )

            // Convert bytes back to string
            return String(
                decryptedBytes,
                Charsets.UTF_8
            )
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
     * Generate RSA key pair
     */
    val keyPair = SecureRSA.generateKeyPair()


    /**
     * Public key
     * Used for encryption
     */
    val publicKey = keyPair.public


    /**
     * Private key
     * Used for decryption
     */
    val privateKey = keyPair.private


    /**
     * STEP 2:
     * Encrypt text
     */
    val encrypted = SecureRSA.encryptWithRSA(
        "Hello World",
        publicKey
    )

    println("Encrypted:")
    println(encrypted)


    /**
     * STEP 3:
     * Decrypt text
     */
    val decrypted = SecureRSA.decryptWithRSA(
        encrypted,
        privateKey
    )

    println()

    println("Decrypted:")
    println(decrypted)
}


/**
 * ============================================================
 * SAMPLE OUTPUT
 * ============================================================
 *
 * Encrypted:
 * Jshs72JshHshs82Jsh...
 *
 * Decrypted:
 * Hello World
 *
 * ============================================================
 *
 * IMPORTANT NOTES
 * ============================================================
 *
 * ✔ Public key encrypts data
 * ✔ Private key decrypts data
 * ✔ More secure for communication
 * ✔ Slower than AES
 * ✔ Best for small data
 *
 * ============================================================
 */