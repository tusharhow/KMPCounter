import io.github.kmpcounter.KMPCounter
import io.github.kmpcounter.toHumanReadable

fun main() {
    println("=== KMPCounter Simple Test ===")
    
    // Test basic functionality
    println("Testing basic formatting:")
    println("1000 -> ${KMPCounter.format(1000)}")
    println("1500 -> ${KMPCounter.format(1500)}")
    println("1000000 -> ${KMPCounter.format(1000000)}")
    
    // Test extension functions
    println("\nTesting extension functions:")
    println("1000.toHumanReadable() -> ${1000.toHumanReadable()}")
    println("1500L.toHumanReadable() -> ${1500L.toHumanReadable()}")
    
    // Test precision
    println("\nTesting precision:")
    println("1234 with precision 0 -> ${KMPCounter.format(1234, 0)}")
    println("1234 with precision 1 -> ${KMPCounter.format(1234, 1)}")
    println("1234 with precision 2 -> ${KMPCounter.format(1234, 2)}")
    
    // Test full words
    println("\nTesting full words:")
    println("1000 full words -> ${KMPCounter.format(1000, useFullWords = true)}")
    println("1500000 full words -> ${KMPCounter.format(1500000, useFullWords = true)}")
    
    println("\n=== Test Complete ===")
} 