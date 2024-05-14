package mockito

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AddCreateMock1Test {
     var add: Add? = null
     var validNumber: ValidNumber? = null
     var print: Print? = null

    @BeforeEach
    fun setUp(){
        validNumber = Mockito.mock(ValidNumber::class.java)
        print = Mockito.mock(Print::class.java)
        add = Add(validNumber!!, print!!)
    }
    @Test
    fun addTest(){
        add?.add(3,2)
        Mockito.verify(validNumber)?.check(3)
    }
}