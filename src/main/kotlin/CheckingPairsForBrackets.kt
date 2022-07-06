class CheckingPairsForBrackets(
    private var inputString: String
) {

    private var resultCheckingString = true
    private var resultCheckingSymbol = false
    private var checkingSymbol = false
    private var errorInOpeningSymbol = false

    private val listAllSymbols = mutableMapOf<Int, Char>()
    private val listCheckingSymbols = mutableListOf<Int>()

    private val listFailInNumbersOpenSymbols = mutableListOf<Int>()
    private val listFailInNumbersCloseSymbols = mutableListOf<Int>()

    private var printResultsString = ""
    private var errorNumberSymbol = 0

    fun getResultsString():String{
        return printResultsString
    }

    fun start() {

        convertingStringToList(inputString, listAllSymbols)

        if (searchLine()) {
            searchOpeningSymbolForClosing()
            searchClosingSymbolForOpening()
            printResults(true)
        } else
            printResults(false)
    }

    private fun convertingStringToList(inputString: String, listAllSymbols: MutableMap<Int, Char>) {

        for (i in inputString.indices) {
            listAllSymbols[i] = inputString[i]
        }
    }

    private fun searchLine(): Boolean {

        listAllSymbols.forEach {
            if (it.value == ')' || it.value == ']' || it.value == '>' || it.value == '}' || it.value == '(' || it.value == '[' || it.value == '<' || it.value == '{')
                return true
        }
        return false
    }

    private fun searchOpeningSymbolForClosing() {

        listAllSymbols.forEach {
            if (it.value == ')' || it.value == ']' || it.value == '>' || it.value == '}') {
                val checkingSymbol = checkingClosingSymbols(it.key, it.value)

                if (!checkingSymbol) resultCheckingString = false
            }
        }
    }

    private fun searchClosingSymbolForOpening() {

        listAllSymbols.forEach {
            if (it.value == '(' || it.value == '[' || it.value == '<' || it.value == '{') {
                val checkingSymbol = checkingOpeningSymbols(it.key, it.value)

                if (!checkingSymbol) resultCheckingString = false
            }
        }
    }

    private fun printResults(result:Boolean) {

        if (result) {

            if (resultCheckingString) {
                printResultsString = inputString + " " + Constants.SEPARATING_SYMBOL + " " + Constants.SUCCESSFUL_MESSAGE

            } else {

                if (errorInOpeningSymbol) {
                    errorNumberSymbol = listFailInNumbersCloseSymbols.first() + 1
                    printResultsString = inputString + " " + Constants.SEPARATING_SYMBOL + " " + errorNumberSymbol
                }
                else {
                    errorNumberSymbol = listFailInNumbersOpenSymbols.first() + 1
                    printResultsString = inputString + " " + Constants.SEPARATING_SYMBOL + " " + errorNumberSymbol
                }
            }

        }
        else printResultsString = Constants.UNSUCCESSFUL_MESSAGE

        println(printResultsString)
    }

    private fun checkingClosingSymbols(key: Int, value: Char): Boolean { //

        resultCheckingSymbol = false
        checkingSymbol = false

        for (i in key downTo 0) {
            findingPairForSymbol(i, value)
        }

        if (!resultCheckingSymbol) searchErrSymbolNumberForClosing(key)

        return resultCheckingSymbol
    }

    private fun checkingOpeningSymbols(key: Int, value: Char): Boolean {

        resultCheckingSymbol = false
        checkingSymbol = false

        for (i in key until listAllSymbols.size) {
            findingPairForSymbol(i, value)
        }

        if (!resultCheckingSymbol) searchErrSymbolNumberForOpening(key)

        return resultCheckingSymbol
    }

    private fun findingPairForSymbol(i: Int, value: Char) {

        var checkUseSymbol = true

        if (listAllSymbols[i] == reversSymbol(value)) {
            if (!checkingSymbol) {
                checkingSymbol = true

                listCheckingSymbols.forEach {
                    if (it == i) {
                        checkUseSymbol = false
                        checkingSymbol = false
                    }
                }

                if (checkUseSymbol) {
                    listCheckingSymbols.add(i)
                    resultCheckingSymbol = true
                }
            }
        }
    }

    private fun searchErrSymbolNumberForOpening(key: Int) {

        var errElement = -1

        for (i in key until listAllSymbols.size) {
            if (listAllSymbols[i] == ')' || listAllSymbols[i] == ']' || listAllSymbols[i] == '}' || listAllSymbols[i] == '>')
                errElement = i
        }

        if (errElement != -1)
            listFailInNumbersOpenSymbols.add(errElement)
        else
            listFailInNumbersOpenSymbols.add(key)

    }

    private fun searchErrSymbolNumberForClosing(key: Int) {

        listFailInNumbersCloseSymbols.add(key)
        errorInOpeningSymbol = true
    }

    private fun reversSymbol(chr: Char?): Char {
        return when (chr) {
            '(' -> ')'
            '[' -> ']'
            '{' -> '}'
            '<' -> '>'

            ')' -> '('
            ']' -> '['
            '}' -> '{'
            '>' -> '<'

            else -> 'N'
        }
    }

}
