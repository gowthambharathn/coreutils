package infinity.developers.coreutils.EngineUtils.OTG


/**
 * Created by Gowtham Barath
 * Date: 26-04-2026
 */

import kotlin.random.Random

fun generateRandomString(
    length: Int? = null,
    strongness: String? = null
): String {

    val finalLength = length ?: 8
    val finalStrongness = (strongness ?: "medium").lowercase()

    val lower = "abcdefghijklmnopqrstuvwxyz"
    val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val numbers = "0123456789"
    val symbols = "!@#\$%^&*()-_=+<>?"

    val chars = when (finalStrongness) {
        "low" -> lower
        "medium" -> lower + upper + numbers
        "high", "strong" -> lower + upper + numbers + symbols
        else -> lower + upper + numbers
    }

    return (1..finalLength)
        .map { chars[Random.nextInt(chars.length)] }
        .joinToString("")
}