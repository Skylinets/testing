package mockito

class ValidNumber {

    fun check(number: Any) : Boolean {
        return if(number is Int){
            number in 0..9
        } else {
            false
        }
    }

    fun checkZero(o: Any?): Boolean {
        return if(o is Int){
            if(o == 0){
                throw ArithmeticException("No podemos aceptar cero")
            } else {
                true
            }
        } else {
            false
        }
    }
    fun doubleToInt(o: Any?): Int {
        return if (o is Double){
            o.toInt()
        } else {
            0
        }
    }
}