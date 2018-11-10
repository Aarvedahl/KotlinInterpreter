enum class TokenType {
    INTEGER, PLUS, MINUS, END_OF_FILE
}

data class Token(val type: TokenType, val value: Char?)


class Parser {


}

class Interpreter {
    var text = ""
    var currentPosition = 0

    fun getCurrentToken(): Token {
        currentPosition++
        val currentCharacter = text.get(currentPosition)

        while (currentCharacter.isWhitespace()) {
            skipWhitespace()
        }

        if (currentCharacter.isDigit()) {
            return Token(TokenType.INTEGER, currentCharacter)
        }

        if(currentCharacter.equals('+')) {
            return Token(TokenType.PLUS, currentCharacter)
        }

        if(currentCharacter.equals('-')) {
            return Token(TokenType.MINUS, currentCharacter)
        }

        return Token(TokenType.END_OF_FILE, null)
    }

    private fun skipWhitespace() {
        if (currentPosition < text.length) {
            currentPosition++
        } else {
            print("Error while trying to skip whitespace")
        }
    }

}


fun main(args: Array<String>) {

    print("Test")

}