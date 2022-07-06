import Constants.SEPARATING_SYMBOL
import Constants.SUCCESSFUL_MESSAGE
import Constants.UNSUCCESSFUL_MESSAGE
import Utils.Companion.checkingPairsForBrackets
import junit.framework.Assert.assertEquals
import org.junit.Test

class Tests {

    @Test
    fun positiveTest1() {
        val inputString = "(){12}(po1)(<12>)"
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL $SUCCESSFUL_MESSAGE")
    }

    @Test
    fun positiveTest2() {
        val inputString = "{12}([w])1"
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL $SUCCESSFUL_MESSAGE")
    }

    @Test
    fun positiveTest3() {
        val inputString = "({12}(po1)<12>)"
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL $SUCCESSFUL_MESSAGE")
    }

    @Test
    fun positiveTest4() {
        val inputString = "123-{1}"
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL $SUCCESSFUL_MESSAGE")
    }

    @Test
    fun negativeTest1() {
        val inputString = "{[}"
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL 3")
    }

    @Test
    fun negativeTest2() {
        val inputString = "([]))["
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL 5")
    }

    @Test
    fun negativeTest3() {
        val inputString = "foo(bar[i)"
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL 10")
    }

    @Test
    fun negativeTest4() {
        val inputString = ")[1]2[3]("
        assertEquals("Error", checkingPairsForBrackets(inputString), "$inputString $SEPARATING_SYMBOL 1")
    }

    @Test
    fun failTest1() {
        val inputString = "123"
        assertEquals("Error", checkingPairsForBrackets(inputString), UNSUCCESSFUL_MESSAGE)
    }

    @Test
    fun failTest2() {
        val inputString = ""
        assertEquals("Error", checkingPairsForBrackets(inputString), UNSUCCESSFUL_MESSAGE)
    }
}