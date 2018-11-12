package Interpreter

enum class TokenType {
    INTEGER, PLUS, MINUS, END_OF_FILE
}

data class Token(val type: TokenType, val value: Char?)


class Parser {

    fun parseTokens(text: String): ArrayList<Token> {
        val tokens = ArrayList<Token>()
        val interpreter = Interpreter(text)

        val totalNumberOfChar = text.length

        for (charPosition in 0..totalNumberOfChar) {
            val token = interpreter.getCurrentToken()
            tokens.add(token)
        }

        return tokens
    }

    fun addResult(tokenList: List<Token>): Int {
        var result = 0
        var x = 0
        while (x < tokenList.size) {
            val currentToken = tokenList.get(x)

            if (currentToken.type == TokenType.PLUS) {
                if (x == tokenList.size || tokenList.get(x + 1).type != TokenType.INTEGER) {
                    break
                } else {
                    result += Integer.parseInt(tokenList.get(x + 1).value.toString())
                }
            }

            if (currentToken.type == TokenType.INTEGER) {
                result += Integer.parseInt(currentToken.value.toString())
            }

            if (currentToken.type == TokenType.END_OF_FILE) {
                break
            }
            x++
        }

        return result

    }

}

class Interpreter(var text: String) {
    var currentPosition = 0
    fun getCurrentToken(): Token {
        val currentCharacter = text.get(currentPosition)

        while (currentCharacter.isWhitespace()) {
            skipWhitespace()
        }

        if (currentCharacter.isDigit()) {
            return Token(TokenType.INTEGER, currentCharacter)
        }

        if (currentCharacter.equals('+')) {
            return Token(TokenType.PLUS, currentCharacter)
        }

        if (currentCharacter.equals('-')) {
            return Token(TokenType.MINUS, currentCharacter)
        }
        currentPosition++

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
    print("Enter a calculation: ")

    val userInput = readLine()
    if (userInput != null) {
        val parser = Parser()
        val tokenList = parser.parseTokens(userInput)
        print(parser.addResult(tokenList))
    }
}

