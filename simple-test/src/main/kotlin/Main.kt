import io.github.kmpcounter.KMPCounter

fun main() {
    println("Debug testing specific failing values:")
    println("1200 -> '${KMPCounter.format(1200)}'")
    println("1250 with precision 2 -> '${KMPCounter.format(1250, 2)}'")
    println("1000 with precision 2 -> '${KMPCounter.format(1000, 2)}'")
    println("1500 with precision 2 -> '${KMPCounter.format(1500, 2)}'")
    println("2000 with precision 1 -> '${KMPCounter.format(2000, 1)}'")
    println("10000000 with precision 1 -> '${KMPCounter.format(10000000, 1)}'")
} 