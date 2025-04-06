import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.atomic.AtomicIntegerArray

class Lotto {
    private val lottoNumbers: Set<Int> = generateLottoNumbers()
    private val stats = AtomicIntegerArray(8)

    private fun generateLottoNumbers(): Set<Int> {
        return (1..40).shuffled().take(7).toSet()
    }

    fun check(numbers: List<Int>) {
        val matches = numbers.count { it in lottoNumbers }
        stats.incrementAndGet(matches)
    }

    fun getStats(): IntArray {
        return IntArray(8) { stats[it] }
    }

    fun printStats() {
        val total = stats.sum()
        println("Checksum: $total should be 13500000")
        for (i in 0..7) {
            println("$i: ${stats[i]}")
        }
    }
}

fun generateGuess(): List<Int> {
    return (1..40).shuffled(ThreadLocalRandom.current()).take(7)
}

fun main() {
    val totalGuesses = 13_500_000
    val numThreads = Runtime.getRuntime().availableProcessors()
    val guessesPerThread = totalGuesses / numThreads

    val lotto = Lotto()
    val threads = mutableListOf<Thread>()

    val start = System.currentTimeMillis()

    for (i in 0 until numThreads) {
        val thread = Thread {
            println("Thread ${i + 1}/$numThreads starting for $guessesPerThread guesses")
            repeat(guessesPerThread) {
                val guess = generateGuess()
                lotto.check(guess)
            }
        }
        threads += thread
        thread.start()
    }

    threads.forEach { it.join() }

    val end = System.currentTimeMillis()
    println("All joined")
    println("Running time ${end - start} ms")
    lotto.printStats()
}
