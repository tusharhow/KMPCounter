package io.github.kmpcounter

import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.round

/**
 * KMPCounter - A Kotlin Multiplatform library for formatting numbers in human-readable format
 * Converts numbers like 1000 to "1K", 1200 to "1.2K", 1000000 to "1M", etc.
 */
object KMPCounter {
    
    private val suffixes = arrayOf("", "K", "M", "B", "T", "P", "E", "Z", "Y")
    
    /**
     * Formats a number into human-readable format
     * @param number The number to format
     * @param precision Number of decimal places (default: 1)
     * @param useFullWords Whether to use full words (thousand, million) instead of letters (K, M)
     * @return Formatted string representation
     */
    fun format(
        number: Long,
        precision: Int = 1,
        useFullWords: Boolean = false
    ): String {
        return formatNumber(number.toDouble(), precision, useFullWords)
    }
    
    /**
     * Formats a number into human-readable format
     * @param number The number to format
     * @param precision Number of decimal places (default: 1)
     * @param useFullWords Whether to use full words (thousand, million) instead of letters (K, M)
     * @return Formatted string representation
     */
    fun format(
        number: Int,
        precision: Int = 1,
        useFullWords: Boolean = false
    ): String {
        return formatNumber(number.toDouble(), precision, useFullWords)
    }
    
    /**
     * Formats a number into human-readable format
     * @param number The number to format
     * @param precision Number of decimal places (default: 1)
     * @param useFullWords Whether to use full words (thousand, million) instead of letters (K, M)
     * @return Formatted string representation
     */
    fun format(
        number: Double,
        precision: Int = 1,
        useFullWords: Boolean = false
    ): String {
        return formatNumber(number, precision, useFullWords)
    }
    
    /**
     * Formats a number into human-readable format
     * @param number The number to format
     * @param precision Number of decimal places (default: 1)
     * @param useFullWords Whether to use full words (thousand, million) instead of letters (K, M)
     * @return Formatted string representation
     */
    fun format(
        number: Float,
        precision: Int = 1,
        useFullWords: Boolean = false
    ): String {
        return formatNumber(number.toDouble(), precision, useFullWords)
    }
    
    private fun formatNumber(
        number: Double,
        precision: Int,
        useFullWords: Boolean
    ): String {
        if (number == 0.0) return "0"
        
        val absNumber = abs(number)
        val sign = if (number < 0) "-" else ""
        
        // For numbers less than 1000, return as is
        if (absNumber < 1000) {
            return if (absNumber == absNumber.toLong().toDouble()) {
                "$sign${absNumber.toLong()}"
            } else {
                val formatted = formatDecimal(absNumber, precision)
                "$sign${formatted.trimEnd('0').trimEnd('.')}"
            }
        }
        
        // Calculate the appropriate suffix index
        val suffixIndex = (log10(absNumber) / 3).toInt().coerceAtMost(suffixes.size - 1)
        val divisor = powerOf10(suffixIndex * 3)
        val scaledNumber = absNumber / divisor
        
        // Format the number
        val formattedNumber = if (scaledNumber == scaledNumber.toLong().toDouble() && precision == 0) {
            scaledNumber.toLong().toString()
        } else {
            val formatted = formatDecimal(scaledNumber, precision)
            formatted.trimEnd('0').trimEnd('.')
        }
        
        val suffix = if (useFullWords) {
            getFullWordSuffix(suffixIndex)
        } else {
            suffixes[suffixIndex]
        }
        
        return "$sign$formattedNumber$suffix"
    }
    
    private fun getFullWordSuffix(index: Int): String {
        return when (index) {
            0 -> ""
            1 -> " thousand"
            2 -> " million"
            3 -> " billion"
            4 -> " trillion"
            5 -> " quadrillion"
            6 -> " quintillion"
            7 -> " sextillion"
            8 -> " septillion"
            else -> " × 10^${index * 3}"
        }
    }
    
    private fun powerOf10(exponent: Int): Double {
        var result = 1.0
        repeat(exponent) {
            result *= 10.0
        }
        return result
    }
    
    private fun formatDecimal(number: Double, precision: Int): String {
        if (precision == 0) {
            return number.toLong().toString()
        }
        
        val multiplier = when (precision) {
            1 -> 10.0
            2 -> 100.0
            3 -> 1000.0
            4 -> 10000.0
            5 -> 100000.0
            else -> powerOf10(precision)
        }
        
        val rounded = round(number * multiplier) / multiplier
        val integerPart = rounded.toLong()
        val fractionalPart = rounded - integerPart
        
        if (fractionalPart == 0.0) {
            return integerPart.toString()
        }
        
        val fractionalString = (fractionalPart * multiplier).toLong().toString().padStart(precision, '0')
        return "$integerPart.$fractionalString"
    }
}

/**
 * Extension function to format Long numbers
 */
fun Long.toHumanReadable(precision: Int = 1, useFullWords: Boolean = false): String {
    return KMPCounter.format(this, precision, useFullWords)
}

/**
 * Extension function to format Int numbers
 */
fun Int.toHumanReadable(precision: Int = 1, useFullWords: Boolean = false): String {
    return KMPCounter.format(this, precision, useFullWords)
}

/**
 * Extension function to format Double numbers
 */
fun Double.toHumanReadable(precision: Int = 1, useFullWords: Boolean = false): String {
    return KMPCounter.format(this, precision, useFullWords)
}

/**
 * Extension function to format Float numbers
 */
fun Float.toHumanReadable(precision: Int = 1, useFullWords: Boolean = false): String {
    return KMPCounter.format(this, precision, useFullWords)
}
