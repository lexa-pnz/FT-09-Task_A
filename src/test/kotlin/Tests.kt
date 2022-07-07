import Constants.SEPARATING_SYMBOL
import Constants.SUCCESSFUL_MESSAGE
import Constants.UNSUCCESSFUL_MESSAGE
import Utils.Companion.checkingPairsForBrackets
import junit.framework.Assert.assertEquals
import org.junit.Test

class Tests {

    @Test
    fun positiveTest1() {
        positiveTest("(){12}(po1)(<12>)")
    }

    @Test
    fun positiveTest2() {
        positiveTest("{12}([w])1")
    }

    @Test
    fun positiveTest3() {
        positiveTest("({12}(po1)<12>)")
    }

    @Test
    fun positiveTest4() {
        positiveTest("123-{1}")
    }

    @Test
    fun negativeTest1() {
        negativeTest("{[}", 3)
    }

    @Test
    fun negativeTest2() {
        negativeTest("([]))[", 5)
    }

    @Test
    fun negativeTest3() {
        negativeTest("foo(bar[i)", 10)
    }

    @Test
    fun negativeTest4() {
        negativeTest(")[1]2[3](", 1)
    }

    @Test
    fun failTest1() {
        failTest("123")
    }

    @Test
    fun failTest2() {
        failTest("")
    }

    private fun positiveTest(inputString:String) {
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL $SUCCESSFUL_MESSAGE")
    }

    private fun negativeTest(inputString:String, numberFailSymbol:Int) {
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL $numberFailSymbol")
    }

    private fun failTest(inputString:String) {
        assertEquals("Error", checkingPairsForBrackets(inputString), UNSUCCESSFUL_MESSAGE)
    }
}
