class Utils {

    companion object {

        fun checkingPairsForBrackets(inputString: String): String {
            val checkingPairsForBrackets = CheckingPairsForBrackets(inputString)
            checkingPairsForBrackets.start()
            return checkingPairsForBrackets.getResultsString()
        }
    }
}