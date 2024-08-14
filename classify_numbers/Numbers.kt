import kotlin.math.sqrt

enum class NumberType {
    PRIME, EVEN, ODD
}

// Interfaz común para los números
interface IBaseNumber {
    val value: Int
    fun printValue()
}

// Clase para números primos
class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("Prime Number: $value")
    }
}

// Clase para números impares
class OddNumber(override val value: Int) : IBaseNumber {
    private val divisors: List<Int> = findDivisors(value)

    override fun printValue() {
        println("Odd Number: $value")
        println("Divisors: $divisors")
    }

    private fun findDivisors(number: Int): List<Int> {
        return (1..number / 2).filter { number % it == 0 }
    }
}

// Clase para números pares
class EvenNumber(override val value: Int) : IBaseNumber {
    private val divisors: List<Int> = findDivisors(value)

    override fun printValue() {
        println("Even Number: $value")
        println("Divisors: $divisors")
    }

    private fun findDivisors(number: Int): List<Int> {
        return (1..number / 2).filter { number % it == 0 }
    }
}

// Contenedor para los resultados de la evaluación
class EvaluationResult {
    val primes: MutableList<PrimeNumber> = mutableListOf()
    val evens: MutableList<EvenNumber> = mutableListOf()
    val odds: MutableList<OddNumber> = mutableListOf()
}

class PrimeNumberProcessor {
    private val result = EvaluationResult()

    fun processNumbers(numbers: List<Int>) {
        for (number in numbers) {
            val numberType = validateNumber(number)
            val numberObject = createNumberObject(number, numberType)
            numberObject?.let { classifyNumber(it) }
        }
        printResults()
    }

    // Método privado que valida y determina el tipo de número basado en los divisores
    private fun validateNumber(number: Int): NumberType {
        return when {
            isPrime(number) -> NumberType.PRIME
            number % 2 == 0 -> NumberType.EVEN
            else -> NumberType.ODD
        }
    }


    // Método que crea un objeto del tipo correcto basado en NumberType
    private fun createNumberObject(number: Int, numberType: NumberType): IBaseNumber? {
        return when (numberType) {
            NumberType.PRIME -> PrimeNumber(number)
            NumberType.EVEN -> EvenNumber(number)
            NumberType.ODD -> OddNumber(number)
        }
    }

    private fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        if (number <= 3) return true
        if (number % 2 == 0 || number % 3 == 0) return false
        for (i in 5..sqrt(number.toDouble()).toInt() step 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false
        }
        return true
    }

    private fun classifyNumber(numberObject: IBaseNumber) {
        when (numberObject) {
            is PrimeNumber -> result.primes.add(numberObject)
            is EvenNumber -> result.evens.add(numberObject)
            is OddNumber -> result.odds.add(numberObject)
        }
    }

    private fun printResults() {
        result.primes.forEach { it.printValue() }
        result.evens.forEach { it.printValue() }
        result.odds.forEach { it.printValue() }
    }
}

fun main() {
    val processor = PrimeNumberProcessor()
    processor.processNumbers(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
}

