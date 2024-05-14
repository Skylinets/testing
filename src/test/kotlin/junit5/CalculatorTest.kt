package junit5

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Duration

class CalculatorTest {

    companion object {
        private var calculator: Calculator? = null
        @JvmStatic
        @BeforeAll
        fun beforeAll(){
            calculator = Calculator()
            println("@BeforeAll -> beforeAll()")
        }
    }

    @BeforeEach
    fun setUp() {
        //calculator = junit5.Calculator()
        println(" @BeforeEach -Z setUp()")
    }

    @AfterEach
    fun tearDown() {
       // calculator = null
        println("@AfterEach -Z  tearDown()")
    }

    @Test
    fun calculatorNotNullTest() {
        assertNotNull(calculator, "junit5.Calculator debe ser not null")
        println(" @TEST -Z calculatorNotNullTest()")
    }

    @Test
    fun `calculator instance`() {
        assertNotNull(calculator, "junit5.Calculator debe ser not null")
        println(" @TEST -Z calculator instance()")
    }

    @Test
    fun addAssertTest() {
        //SetUp
        val calculatorAssert = Calculator()
        val resultExpected = 30
        //Action
        val result = calculatorAssert.add(10, 20)
        //Assert
        assertEquals(resultExpected, result)
        println("@Test -Z addAssertTest()")
    }

    @Test
    fun addTest() {
        assertEquals(30, calculator?.add(10, 20))
        println("@Test -Z addTest()")
    }

    @Test
    fun assertTypesTest() {
        assertTrue(1 == 1)
        assertFalse(1 == 2)
        assertNull(null)
        assertNotNull(calculator)
        var calculator1 = Calculator()
        var calculator3 = calculator1

        assertSame(calculator1, calculator3)

        assertEquals("alberto", "alberto")
        assertEquals("alberto", "alberto", "Ha Fallado nuestro metodo de conversion a minusculas")
    }

    @Test
    fun divideTest() {
        assertEquals(2, calculator?.divide(4, 2))
        println("@Test -Z  divideTest()")
    }

    @Test
    fun divideNotValidInput() {
        //   fail<Int>("Fallo detectado manualmente - No se puede divicir por cero")
    }

    @Test
    fun divideByZero_invalidInput_ExpectedException() {
        assertThrows(
            ArithmeticException::class.java,
            { calculator?.divideByZero(2, 0) },
            "no se puede dividir por cero"
        )
    }

    @Disabled("disabled util but 23 be resolved")
    @Test
    fun divideByZeroTest() {
        assertEquals(2, calculator?.divide(5, 0))
    }

    @Test
    fun add_Assert_all_test() {
        assertAll(
            { assertEquals(30, calculator?.add(10, 20)) },
            { assertEquals(30, calculator?.add(10, 20)) },
            { assertEquals(2, calculator?.add(1, 1)) }
        )
    }
    @Nested
    inner class AddCreateMock1Test{
        @Test
        fun add_Positive_Number_Test(){
            assertEquals(30, calculator?.add(15,15))
        }
        @Test
        fun add_Negative_Number_Test(){
            assertEquals(-30, calculator?.add(-15,-15))
        }
        @Test
        fun add_Zero_Number_Test(){
            assertEquals(0, calculator?.add(15,-15))
        }
    }

    @Test
    fun timeOut_Test(){
        org.junit.jupiter.api.assertTimeout(Duration.ofMillis(1016)){ calculator?.longTaskOperation()}
    }
}