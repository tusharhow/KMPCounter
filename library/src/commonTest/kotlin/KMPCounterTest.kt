package io.github.kmpcounter

import kotlin.test.Test
import kotlin.test.assertEquals

class KMPCounterTest {

    @Test
    fun testBasicFormatting() {
        // Test numbers under 1000
        assertEquals("0", KMPCounter.format(0))
        assertEquals("1", KMPCounter.format(1))
        assertEquals("99", KMPCounter.format(99))
        assertEquals("999", KMPCounter.format(999))
        
        // Test thousands
        assertEquals("1K", KMPCounter.format(1000))
        assertEquals("1.2K", KMPCounter.format(1200))
        assertEquals("1.5K", KMPCounter.format(1500))
        assertEquals("10K", KMPCounter.format(10000))
        assertEquals("100K", KMPCounter.format(100000))
        
        // Test millions
        assertEquals("1M", KMPCounter.format(1000000))
        assertEquals("1.2M", KMPCounter.format(1200000))
        assertEquals("10M", KMPCounter.format(10000000))
        assertEquals("100M", KMPCounter.format(100000000))
        
        // Test billions
        assertEquals("1B", KMPCounter.format(1000000000))
        assertEquals("1.2B", KMPCounter.format(1200000000))
    }

    @Test
    fun testNegativeNumbers() {
        assertEquals("-1", KMPCounter.format(-1))
        assertEquals("-1K", KMPCounter.format(-1000))
        assertEquals("-1.2K", KMPCounter.format(-1200))
        assertEquals("-1M", KMPCounter.format(-1000000))
        assertEquals("-1B", KMPCounter.format(-1000000000))
    }

    @Test
    fun testPrecision() {
        assertEquals("1K", KMPCounter.format(1234, 0))
        assertEquals("1.2K", KMPCounter.format(1234, 1))
        assertEquals("1.23K", KMPCounter.format(1234, 2))
        assertEquals("1.234K", KMPCounter.format(1234, 3))
    }

    @Test
    fun testFullWords() {
        assertEquals("1 thousand", KMPCounter.format(1000, useFullWords = true))
        assertEquals("1.2 thousand", KMPCounter.format(1200, useFullWords = true))
        assertEquals("1 million", KMPCounter.format(1000000, useFullWords = true))
        assertEquals("1.5 million", KMPCounter.format(1500000, useFullWords = true))
        assertEquals("1 billion", KMPCounter.format(1000000000, useFullWords = true))
        assertEquals("2 trillion", KMPCounter.format(2000000000000L, useFullWords = true))
    }

    @Test
    fun testDifferentNumberTypes() {
        // Test Int
        assertEquals("1K", KMPCounter.format(1000))
        
        // Test Long
        assertEquals("1K", KMPCounter.format(1000L))
        
        // Test Double
        assertEquals("1K", KMPCounter.format(1000.0))
        assertEquals("1.5K", KMPCounter.format(1500.0))
        
        // Test Float
        assertEquals("1K", KMPCounter.format(1000f))
        assertEquals("1.5K", KMPCounter.format(1500f))
    }

    @Test
    fun testExtensionFunctions() {
        assertEquals("1K", 1000.toHumanReadable())
        assertEquals("1K", 1000L.toHumanReadable())
        assertEquals("1K", 1000.0.toHumanReadable())
        assertEquals("1K", 1000f.toHumanReadable())
        
        // Test with parameters
        assertEquals("1K", 1234.toHumanReadable(0))
        assertEquals("1.2K", 1234.toHumanReadable(1))
        assertEquals("1 thousand", 1000.toHumanReadable(useFullWords = true))
    }

    @Test
    fun testEdgeCases() {
        // Very small decimals
        assertEquals("0.1", KMPCounter.format(0.1))
        assertEquals("0.5", KMPCounter.format(0.5))
        
        // Numbers just under thresholds
        assertEquals("999", KMPCounter.format(999))
        assertEquals("999K", KMPCounter.format(999000))
        assertEquals("999M", KMPCounter.format(999000000))
        
        // Large numbers
        assertEquals("1T", KMPCounter.format(1000000000000L))
        assertEquals("1P", KMPCounter.format(1000000000000000L))
    }

    @Test
    fun testDecimalTrimming() {
        // Should remove trailing zeros
        assertEquals("1K", KMPCounter.format(1000, 2))
        assertEquals("1.5K", KMPCounter.format(1500, 2))
        assertEquals("1.25K", KMPCounter.format(1250, 2))
        
        // Should handle whole numbers correctly
        assertEquals("2K", KMPCounter.format(2000, 1))
        assertEquals("10M", KMPCounter.format(10000000, 1))
    }

    @Test
    fun testSpecialValues() {
        // Test zero with different precisions
        assertEquals("0", KMPCounter.format(0, 0))
        assertEquals("0", KMPCounter.format(0, 1))
        assertEquals("0", KMPCounter.format(0, 2))
        
        // Test very large numbers
        val veryLarge = 1234567890123456789L
        val result = KMPCounter.format(veryLarge)
        // Should handle gracefully without crashing
//        assert(result.isNotEmpty())
    }
}