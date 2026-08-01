package infinity.developers.coreutils.Database.Search

import java.util.Locale

/**
 * Tokenizer
 * Converts text into searchable tokens
 */
object Tokenizer {

    /** Basic tokenize */
    fun tokenize(text: String): List<String> {
        return text
            .lowercase(Locale.getDefault())
            .replace(Regex("[^a-z0-9 ]"), " ")
            .split(Regex("\\s+"))
            .filter { it.isNotBlank() }
    }

    /** Unique tokens only */
    fun uniqueTokens(text: String): List<String> {
        return tokenize(text).distinct()
    }

    /** Remove common stop words */
    fun tokenizeClean(text: String): List<String> {

        val stopWords = setOf(
            "a", "an", "the", "is", "are",
            "was", "were", "of", "to",
            "in", "on", "at", "for",
            "and", "or", "with"
        )

        return tokenize(text)
            .filterNot { it in stopWords }
    }

    /** Token frequency map */
    fun frequency(text: String): Map<String, Int> {
        return tokenize(text)
            .groupingBy { it }
            .eachCount()
    }

    /** Rebuild sentence from tokens */
    fun join(tokens: List<String>): String {
        return tokens.joinToString(" ")
    }
}