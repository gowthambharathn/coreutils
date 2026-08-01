package infinity.developers.coreutils.EngineUtils.Security.Encryption


import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * Created by Gowtham Barath
 * Date: 17-05-2026
 */

class SecureAES {

    companion object {

        private const val AES_MODE = "AES/GCM/NoPadding"
        private const val GCM_TAG_LENGTH = 128

        // Generate AES Key
        fun generateKey(): SecretKey {
            val keyGenerator = KeyGenerator.getInstance("AES")
            keyGenerator.init(256)
            return keyGenerator.generateKey()
        }

        // Encrypt Function
        fun encryptSecureAES(data: String, secretKey: SecretKey): String {

            val cipher = Cipher.getInstance(AES_MODE)

            // Generate random IV
            val iv = ByteArray(12)
            SecureRandom().nextBytes(iv)

            val spec = GCMParameterSpec(GCM_TAG_LENGTH, iv)

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec)

            val encryptedBytes = cipher.doFinal(
                data.toByteArray(StandardCharsets.UTF_8)
            )

            // Combine IV + encrypted data
            val combined = iv + encryptedBytes

            return Base64.encodeToString(combined, Base64.NO_WRAP)
        }

        // Decrypt Function
        fun decryptSecureAES(
            encryptedData: String,
            secretKey: SecretKey
        ): String {

            val combined = Base64.decode(encryptedData, Base64.NO_WRAP)

            // Extract IV
            val iv = combined.copyOfRange(0, 12)

            // Extract encrypted bytes
            val encryptedBytes = combined.copyOfRange(12, combined.size)

            val cipher = Cipher.getInstance(AES_MODE)

            val spec = GCMParameterSpec(GCM_TAG_LENGTH, iv)

            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

            val decryptedBytes = cipher.doFinal(encryptedBytes)

            return String(decryptedBytes, StandardCharsets.UTF_8)
        }
    }
}


/**
 * ============================================================
 * SecureAES Usage Guide
 * ============================================================
 *
 * STEP 1:
 * Generate a secure AES-256 secret key.
 *
 * NOTE:
 * Store this key safely.
 * If the key changes, old encrypted data cannot be decrypted.
 */
val key = SecureAES.generateKey()


/**
 * STEP 2:
 * Encrypt normal text data.
 *
 * INPUT:
 * "Hello World"
 *
 * OUTPUT:
 * Encrypted Base64 string
 */
//val encrypted = SecureAES.encryptSecureAES(
//    "Hello World",
//    key
//)


/**
 * STEP 3:
 * Decrypt encrypted data back to original text.
 *
 * INPUT:
 * encrypted Base64 string
 *
 * OUTPUT:
 * Original text -> "Hello World"
 */
//val decrypted = SecureAES.decryptSecureAES(
//    encrypted,
//    key
//)


/**
 * ============================================================
 * Example Output
 * ============================================================
 *
 * Encrypted:
 * gH72Jshs82JshHshs8sJHsh...
 *
 * Decrypted:
 * Hello World
 *
 * ============================================================
 * Security Features
 * ============================================================
 *
 * ✔ AES-256 Encryption
 * ✔ AES/GCM/NoPadding (Modern Secure Mode)
 * ✔ Random IV generated every encryption
 * ✔ Base64 encoded output
 * ✔ Authentication tag protection
 *
 * ============================================================
 * Best Uses
 * ============================================================
 *
 * - Database encryption
 * - Offline storage security
 * - API request protection
 * - Secure local files
 * - User sensitive data
 *
 * ============================================================
 */