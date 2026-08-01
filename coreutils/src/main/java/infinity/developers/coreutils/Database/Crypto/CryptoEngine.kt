package infinity.developers.coreutils.Database.Crypto

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * Crypto Engine
 * Handles AES encryption / decryption
 */
object CryptoEngine {

    private const val TRANSFORMATION = "AES/GCM/NoPadding"
    private const val KEY_SIZE = 256
    private const val IV_SIZE = 12
    private const val TAG_SIZE = 128

    /** Generate AES Secret Key */
    fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(KEY_SIZE)
        return keyGenerator.generateKey()
    }

    /** Encrypt Text */
    fun encrypt(
        plainText: String,
        secretKey: SecretKey
    ): String {

        val cipher = Cipher.getInstance(TRANSFORMATION)

        val iv = ByteArray(IV_SIZE)
        SecureRandom().nextBytes(iv)

        val spec = GCMParameterSpec(TAG_SIZE, iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec)

        val encrypted = cipher.doFinal(
            plainText.toByteArray(Charsets.UTF_8)
        )

        val finalBytes = iv + encrypted
        return Base64.encodeToString(finalBytes, Base64.NO_WRAP)
    }

    /** Decrypt Text */
    fun decrypt(
        encryptedText: String,
        secretKey: SecretKey
    ): String {

        val allBytes = Base64.decode(encryptedText, Base64.NO_WRAP)

        val iv = allBytes.copyOfRange(0, IV_SIZE)
        val cipherBytes = allBytes.copyOfRange(IV_SIZE, allBytes.size)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(TAG_SIZE, iv)

        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

        val decrypted = cipher.doFinal(cipherBytes)

        return String(decrypted, Charsets.UTF_8)
    }

    /** SHA-256 Hash */
    fun sha256(text: String): String {
        val digest = MessageDigest
            .getInstance("SHA-256")
            .digest(text.toByteArray())

        return digest.joinToString("") {
            "%02x".format(it)
        }
    }
}