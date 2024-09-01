// Interfaz base para los números
interface Numero {
    val valor: Int
    fun tipo(): String
}

// Clase para representar números primos
class NumeroPrimo(override val valor: Int) : Numero {
    override fun tipo(): String = "Primo"

    fun esPrimo(): Boolean {
        if (valor <= 1) return false
        if (valor == 2) return true
        if (valor % 2 == 0) return false
        for (i in 3..Math.sqrt(valor.toDouble()).toInt() step 2) {
            if (valor % i == 0) return false
        }
        return true
    }
}

// Clase para representar números pares
class NumeroPar(override val valor: Int) : Numero {
    override fun tipo(): String = "Par"

    fun esPar(): Boolean = valor % 2 == 0
}

// Clase para representar números impares
class NumeroImpar(override val valor: Int) : Numero {
    override fun tipo(): String = "Impar"

    fun esImpar(): Boolean = valor % 2 != 0
}

// Función para contar los números primos, pares e impares
fun contarNumeros(n: Int): Triple<Int, Int, Int> {
    if (n <= 0) throw IllegalArgumentException("El número N debe ser mayor que 0.")

    var conteoPrimos = 0
    var conteoPares = 0
    var conteoImpares = 0

    for (i in 1..n) {
        val numeroPrimo = NumeroPrimo(i)
        val numeroPar = NumeroPar(i)
        val numeroImpar = NumeroImpar(i)

        if (numeroPrimo.esPrimo()) conteoPrimos++
        if (numeroPar.esPar()) conteoPares++
        if (numeroImpar.esImpar()) conteoImpares++
    }

    return Triple(conteoPrimos, conteoPares, conteoImpares)
}


fun main() {
    try {
        println("Introduce un número entero positivo:")
        val n = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Entrada no válida.")
        val (primos, pares, impares) = contarNumeros(n)
        println("Prime numbers count: $primos")
        println("Even numbers count: $pares")
        println("Odd numbers count: $impares")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
