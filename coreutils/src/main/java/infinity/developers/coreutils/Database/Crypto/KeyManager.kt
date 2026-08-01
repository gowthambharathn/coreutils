package infinity.developers.coreutils.Database.Crypto

import android.content.Context
import android.os.Build
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties

/**
 * Key Manager
 * Handles Android Keystore keys securely
 */
object KeyManager {

    private const val ANDROID_KEYSTORE = "AndroidKeyStore"
    private const val DEFAULT_ALIAS = "SecureDB_Master_Key"

    /** Create key if not exists */
    fun generateKey(alias: String = DEFAULT_ALIAS) {
        if (hasKey(alias)) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                ANDROID_KEYSTORE
            )

            val spec = KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or
                        KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(
                    KeyProperties.ENCRYPTION_PADDING_NONE
                )
                .setKeySize(256)
                .build()

            keyGenerator.init(spec)
            keyGenerator.generateKey()
        }
    }

    /** Get secret key */
    fun getKey(alias: String = DEFAULT_ALIAS): SecretKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)

        val secretKeyEntry =
            keyStore.getEntry(alias, null)
                    as KeyStore.SecretKeyEntry

        return secretKeyEntry.secretKey
    }

    /** Check key exists */
    fun hasKey(alias: String = DEFAULT_ALIAS): Boolean {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)
        return keyStore.containsAlias(alias)
    }

    /** Delete key */
    fun deleteKey(alias: String = DEFAULT_ALIAS) {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)

        if (keyStore.containsAlias(alias)) {
            keyStore.deleteEntry(alias)
        }
    }

    /** Initialize once in app start */
    fun init(context: Context) {
        generateKey()
    }
}