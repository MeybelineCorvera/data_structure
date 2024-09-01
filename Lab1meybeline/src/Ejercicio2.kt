
// Interfaz para FizzBuzz
interface FizzBuzzLogic {
    fun obtenerResultado(numero: Int): String
}

// Clase que implementa la lógica de FizzBuzz
class FizzBuzz : FizzBuzzLogic {
    override fun obtenerResultado(numero: Int): String {
        return when {
            numero % 15 == 0 -> "FizzBuzz"
            numero % 3 == 0 -> "Fizz"
            numero % 5 == 0 -> "Buzz"
            else -> numero.toString()
        }
    }
}

// Función para imprimir los resultados de FizzBuzz
fun imprimirFizzBuzz(rango: Int) {
    if (rango <= 0) throw IllegalArgumentException("El rango debe ser mayor que 0.")

    val fizzBuzzLogic = FizzBuzz()
    val resultados = mutableListOf<String>()

    for (i in 1..rango) {
        resultados.add(fizzBuzzLogic.obtenerResultado(i))
    }

    // Imprimir resultados en líneas de 10 elementos
    resultados.chunked(10).forEach { linea ->
        println(linea.joinToString(" "))
    }
}

// Función principal para manejar errores y ejecutar el programa
fun main() {
    try {
        println("Introduce un número entero positivo para el rango de FizzBuzz:")
        val rango = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Entrada no válida.")
        imprimirFizzBuzz(rango)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
