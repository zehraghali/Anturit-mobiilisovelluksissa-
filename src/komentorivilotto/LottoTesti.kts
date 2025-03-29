fun testGuessedNumbers() {
    assert(guessedNumbers("1, 3, 5, 7, 10, 12, 15") == listOf(1, 3, 5, 7, 10, 12, 15))
    assert(guessedNumbers("1, 3, 5, 7, 10, 10, 12") == listOf(1, 3, 5, 7, 10, 12))
    assert(guessedNumbers("1, 2, a, 4, 5") == listOf(1, 2, 4, 5))
}

fun testIsLegalGuess() {
    assert(isLegalGuess(listOf(1, 2, 3, 4, 5, 6, 7)))
    assert(!isLegalGuess(listOf(1, 2, 3, 4, 5, 6)))
    assert(!isLegalGuess(listOf(1, 2, 3, 4, 5, 6, 41)))
}

fun testEqualsCount() {
    assert(equalsCount(listOf(1, 2, 3), listOf(2, 3, 4)) == 2)
    assert(equalsCount(listOf(1, 2, 3), listOf(4, 5, 6)) == 0)
}
