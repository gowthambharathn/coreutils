package infinity.developers.coreutils.EngineUtils.Security.Encryption


/**
 * Created by Gowtham Barath
 * Date: 17-05-2026
 */

import android.content.Context
import android.provider.Settings
import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * ============================================================
 * Device Lock Encryption
 * ============================================================
 *
 * Type:
 * Device-specific AES encryption
 *
 * PURPOSE:
 * Encrypted data works only on the same device.
 *
 * If encrypted data is copied to another phone,
 * decryption will fail.
 *
 * ============================================================
 *
 * HOW IT WORKS:
 *
 * 1. Get Android Device ID
 * 2. Generate SHA-256 hash from device ID
 * 3. Create AES key from hash
 * 4. Encrypt using device-based key
 *
 * ============================================================
 *
 * Common Uses:
 * ✔ License systems
 * ✔ Secure offline storage
 * ✔ Anti-copy protection
 * ✔ Device-locked databases
 *
 * ============================================================
 */

class DeviceLockEncryption {

    companion object {

        /**
         * AES encryption mode
         */
        private const val AES_MODE =
            "AES/ECB/PKCS5Padding"


        /**
         * ====================================================
         * Generate Device-Based AES Key
         * ====================================================
         *
         * PARAMETERS:
         * context -> Android context
         *
         * RETURNS:
         * AES SecretKeySpec
         *
         * HOW IT WORKS:
         * 1. Read Android ID
         * 2. Hash using SHA-256
         * 3. Use first 32 bytes as AES key
         */
        private fun getDeviceKey(
            context: Context
        ): SecretKeySpec {

            /**
             * Get Android device ID
             */
            val androidId = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )


            /**
             * Generate SHA-256 hash
             */
            val digest =
                MessageDigest.getInstance("SHA-256")

            val hash = digest.digest(
                androidId.toByteArray(
                    StandardCharsets.UTF_8
                )
            )


            /**
             * Create AES key from hash
             */
            return SecretKeySpec(
                hash,
                "AES"
            )
        }


        /**
         * ====================================================
         * Encrypt Data
         * ====================================================
         *
         * PARAMETERS:
         * context -> Android context
         * data    -> Original text
         *
         * RETURNS:
         * Base64 encrypted string
         *
         * IMPORTANT:
         * Only same device can decrypt this.
         */
        fun deviceLockEncrypt(
            context: Context,
            data: String
        ): String {

            // Generate device key
            val secretKey = getDeviceKey(context)

            // Create AES cipher
            val cipher = Cipher.getInstance(AES_MODE)

            // Initialize encrypt mode
            cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey
            )

            // Encrypt text
            val encryptedBytes = cipher.doFinal(
                data.toByteArray(
                    StandardCharsets.UTF_8
                )
            )

            // Convert to Base64
            return Base64.encodeToString(
                encryptedBytes,
                Base64.NO_WRAP
            )
        }


        /**
         * ====================================================
         * Decrypt Data
         * ====================================================
         *
         * PARAMETERS:
         * context       -> Android context
         * encryptedData -> Base64 encrypted text
         *
         * RETURNS:
         * Original text
         *
         * IMPORTANT:
         * Must be decrypted on same device.
         */
        fun deviceLockDecrypt(
            context: Context,
            encryptedData: String
        ): String {

            // Generate same device key
            val secretKey = getDeviceKey(context)

            // Decode Base64
            val encryptedBytes = Base64.decode(
                encryptedData,
                Base64.NO_WRAP
            )

            // Create AES cipher
            val cipher = Cipher.getInstance(AES_MODE)

            // Initialize decrypt mode
            cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey
            )

            // Decrypt bytes
            val decryptedBytes = cipher.doFinal(
                encryptedBytes
            )

            // Convert back to string
            return String(
                decryptedBytes,
                StandardCharsets.UTF_8
            )
        }
    }
}


/**
 * ============================================================
 * USAGE EXAMPLE
 * ============================================================
 */

/*
val encrypted =
    DeviceLockEncryption.deviceLockEncrypt(
        context,
        "Hello World"
    )

println(encrypted)


val decrypted =
    DeviceLockEncryption.deviceLockDecrypt(
        context,
        encrypted
    )

println(decrypted)
*/


/**
 * ============================================================
 * IMPORTANT NOTES
 * ============================================================
 *
 * ✔ Data tied to one device
 * ✔ Another phone cannot decrypt it
 * ✔ Useful for offline app security
 * ✔ Simple implementation
 *
 * WARNING:
 * If device is factory reset,
 * Android ID may change.
 *
 * ============================================================
 */