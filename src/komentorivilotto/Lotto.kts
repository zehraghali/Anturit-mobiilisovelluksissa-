fun guessedNumbers(s: String?): List<Int>? {
    return s?.split(",")?.mapNotNull { it.trim().toIntOrNull() }?.distinct()
}

fun isLegalGuess(guess: List<Int>?): Boolean {
    return guess != null && guess.size == 7 && guess.all { it in 1..40 }
}

fun equalsCount(a: List<Int>?, b: List<Int>?): Int {
    if (a == null || b == null) return 0
    return a.intersect(b).size
}

fun main() {
    val winningNumbers = (1..40).shuffled().take(7).sorted()

    do {
        println("Arvaa 7 eri numeroa väliltä 1-40 (pilkulla erotettuna) tai kirjoita 'quit' lopettaaksesi.")
        val userInput = readlnOrNull()

        if (userInput == "quit") {
            println("Peli päättyi.")
            break
        }

        val guessedList = guessedNumbers(userInput)

        if (isLegalGuess(guessedList)) {
            val correctCount = equalsCount(guessedList, winningNumbers)
            if (correctCount == 7) {
                println("Onnittelut! Arvasit kaikki numerot oikein: $winningNumbers")
                break
            } else {
                println("Arvasit $correctCount numerot oikein. Yritä uudelleen!")
            }
        } else {
            println("Virheellinen syöte. Varmista, että annat 7 eri numeroa väliltä 1-40.")
        }
    } while (true)
}
