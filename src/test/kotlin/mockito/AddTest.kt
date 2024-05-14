package mockito

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.BDDMockito.anyInt
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.stubbing.Answer

class AddTest {

    @InjectMocks
    val add: Add? = null

    @Mock
    val validNumber: ValidNumber? = null

    @Mock
    val print: Print? = null

    @Captor
    val captor: ArgumentCaptor<Int>? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addTest() {
        `when`(validNumber!!.check(3)).thenReturn(true)
        val checkNumber = validNumber!!.check(3)
        assertEquals(true, checkNumber)
        `when`(validNumber!!.check(-3)).thenReturn(false)
        val checkNumber2: Boolean = validNumber!!.check(-3)
        assertEquals(false, checkNumber2)
    }

    @Test
    fun addMockExceptionTest() {
        `when`(validNumber!!.checkZero(0)).thenThrow(ArithmeticException("No podemos aceptar cero"))
        var exception: Exception? = null
        try {
            validNumber.checkZero(0)
        } catch (e: ArithmeticException) {
            exception = e
        }
        assertNotNull(exception)
        Assertions.assertThrows(ArithmeticException::class.java) {
            validNumber!!.checkZero(0)
        }
    }

    @Test
    fun addRealMethodTest() {
        `when`(validNumber!!.check(3)).thenCallRealMethod()
        assertEquals(true, validNumber?.check(3))
        `when`(validNumber!!.check("3")).thenCallRealMethod()
        assertEquals(false, validNumber.check("3"))
    }

    @Test
    fun addDoubleToIntThenAnswerTest() {
        val answers: Answer<Int> = Answer { 7 }
        `when`(validNumber!!.doubleToInt(7.7777)).thenAnswer(answers)
        assertEquals(14, add!!.addInt(7.7777))
    }
    /*
    Arrange : organizar to.do el setup    Given
    Act : el metodo que queremos probar   When
    assert : afirmacion y verificar test  Then
     */

    @Test
    fun aaaPatternTest() {
        //Arrange
        `when`(validNumber?.check(4)).thenReturn(true)
        `when`(validNumber?.check(5)).thenReturn(true)
        //Act
        val result = add!!.add(4, 5)
        //Assert
        assertEquals(9, result)
    }

    @Test
    fun bddPatternTest() {
        //Given
        given(validNumber?.check(4)).willReturn(true)
        given(validNumber?.check(5)).willReturn(true)
        //When
        val result = add!!.add(4, 5)
        //Then
        assertEquals(9, result)
    }

    @Test
    fun argumentMatcherTest() {
        //Given
        given(validNumber?.check(ArgumentMatchers.anyInt())).willReturn(true)
        //When
        val result = add!!.add(4, 5)
        //Then
        assertEquals(9, result)
    }

    @Test
    fun addPrintTest() {
        //Given
        given(validNumber?.check(4)).willReturn(true)
        given(validNumber?.check(5)).willReturn(true)
        //When
        add!!.addPrint(4, 5)
        //Then
        //Mockito.verify(validNumber, Mockito.times(2))!!.check(4)
        Mockito.verify(validNumber)?.check(4)
        Mockito.verify(validNumber)?.check(5)
        Mockito.verify(validNumber, Mockito.never())?.check(9)
        Mockito.verify(validNumber, Mockito.atLeast(1))?.check(4)
        Mockito.verify(validNumber, Mockito.atMost(3))?.check(4)

        Mockito.verify(print)!!.showMessage(9)
        Mockito.verify(print, Mockito.never())!!.showError()
    }


    @Test
    fun captorTest() {
        //given
        given(validNumber?.check(4)).willReturn(true)
        given(validNumber?.check(5)).willReturn(true)
        //when
        add!!.addPrint(4,5)
        //then
        verify(print)!!.showMessage(captor!!.capture())
        assertEquals(9, captor.value)
    }

    @Spy
    var spyList: MutableList<String> = ArrayList()

    @Mock
    var mockList: MutableList<String> = ArrayList()

    @Test
    fun spyTest(){
        spyList.add("1")
        spyList.add("2")
        verify(spyList).add("1")
        verify(spyList).add("2")
        assertEquals(2, spyList.size)
    }
    @Test
    fun mockTest(){
        mockList.add("1")
        mockList.add("2")
        verify(mockList).add("1")
        verify(mockList).add("2")
        given(mockList.size).willReturn(2)
        assertEquals(2, mockList.size)
    }
}