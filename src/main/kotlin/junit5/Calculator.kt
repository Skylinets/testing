package junit5

class Calculator {

    fun add(n1: Int, n2: Int): Int {
        return n1 + n2
    }

    fun subtract(n1: Int, n2: Int): Int {
        return n1 - n2
    }

    fun divide(n1: Int, n2: Int): Int {
        return n1 / n2
    }

    fun divideByZero(n1: Int, n2: Int): Int {
        if (n2 == 0) {
            throw ArithmeticException("No se puede dividir por cero")
        } else {
            return n1 / n2
        }
    }

    fun longTaskOperation() {
        try {
            Thread.sleep(1000)
        } catch (e: Exception) {

        }
    }
}