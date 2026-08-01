package infinity.developers.coreutils.EngineUtils.Security.Encryption


/**
 * Created by Gowtham Barath
 * Date: 17-05-2026
 */

import android.util.Base64

/**
 * ============================================================
 * Custom Obfuscation Class
 * ============================================================
 *
 * Type:
 * Custom data obfuscation
 *
 * IMPORTANT:
 * This is NOT real encryption like AES or RSA.
 *
 * This method is used to:
 * ✔ Hide readable text
 * ✔ Confuse attackers
 * ✔ Add extra security layer
 * ✔ Protect simple local values
 *
 * ============================================================
 *
 * HOW THIS WORKS:
 *
 * 1. Reverse text
 * 2. Shift characters
 * 3. Add random salt text
 * 4. Convert to Base64
 *
 * ============================================================
 */

class CustomObfuscation {

    companion object {

        /**
         * Salt text added to data
         */
        private const val SALT = "X9K2P"


        /**
         * ====================================================
         * Obfuscate Data
         * ====================================================
         *
         * PARAMETERS:
         * data -> Original text
         *
         * RETURNS:
         * Obfuscated Base64 string
         *
         * HOW IT WORKS:
         * 1. Reverse text
         * 2. Shift each character
         * 3. Add salt
         * 4. Encode with Base64
         */
        fun obfuscateCustom(data: String): String {

            /**
             * STEP 1:
             * Reverse original text
             *
             * Example:
             * Hello -> olleH
             */
            val reversed = data.reversed()


            /**
             * STEP 2:
             * Shift each character by +3
             *
             * Example:
             * a -> d
             */
            val shifted = reversed.map {

                (it.code + 3).toChar()

            }.joinToString("")


            /**
             * STEP 3:
             * Add salt text
             */
            val salted = "$SALT$shifted$SALT"


            /**
             * STEP 4:
             * Convert to Base64
             */
            return Base64.encodeToString(
                salted.toByteArray(Charsets.UTF_8),
                Base64.NO_WRAP
            )
        }


        /**
         * ====================================================
         * Deobfuscate Data
         * ====================================================
         *
         * PARAMETERS:
         * encryptedData -> Obfuscated Base64 string
         *
         * RETURNS:
         * Original text
         *
         * HOW IT WORKS:
         * 1. Decode Base64
         * 2. Remove salt
         * 3. Reverse character shifting
         * 4. Reverse text again
         */
        fun deobfuscateCustom(
            encryptedData: String
        ): String {

            /**
             * STEP 1:
             * Decode Base64
             */
            val decoded = String(
                Base64.decode(
                    encryptedData,
                    Base64.NO_WRAP
                ),
                Charsets.UTF_8
            )


            /**
             * STEP 2:
             * Remove salt text
             */
            val removedSalt = decoded
                .removePrefix(SALT)
                .removeSuffix(SALT)


            /**
             * STEP 3:
             * Reverse character shifting
             *
             * Example:
             * d -> a
             */
            val unShifted = removedSalt.map {

                (it.code - 3).toChar()

            }.joinToString("")


            /**
             * STEP 4:
             * Reverse text again
             */
            return unShifted.reversed()
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
     * Original text
     */
    val text = "Hello World"


    /**
     * STEP 2:
     * Obfuscate text
     */
    val encrypted =
        CustomObfuscation.obfuscateCustom(text)

    println("Obfuscated:")
    println(encrypted)


    /**
     * STEP 3:
     * Restore original text
     */
    val decrypted =
        CustomObfuscation.deobfuscateCustom(
            encrypted
        )

    println()

    println("Original:")
    println(decrypted)
}


/**
 * ============================================================
 * SAMPLE OUTPUT
 * ============================================================
 *
 * Obfuscated:
 * WDlLMlNnb3V6UiNyb29oS1g5SzJQ
 *
 * Original:
 * Hello World
 *
 * ============================================================
 *
 * IMPORTANT NOTES
 * ============================================================
 *
 * ✔ Lightweight protection
 * ✔ Fast execution
 * ✔ Good for app-level hiding
 * ✔ NOT strong enough for banking/security apps
 * ✔ Best when combined with AES
 *
 * ============================================================
 */