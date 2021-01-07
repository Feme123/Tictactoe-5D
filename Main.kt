package tictactoe

import java.util.*
import kotlin.math.abs

fun main() {

    var symbol = "         "
    tablero(symbol)
    var state = getState(symbol)
    var turno = 1

    while (!(state == "X wins" || state == "O wins" || state == "Draw" )) {
        var letra = "X"
        if(turno%2==0){
            letra = "O"
        }

        symbol = jugada(symbol, letra)
        state = getState(symbol)
        turno++

        tablero(symbol)
    }
    println(state)
}
fun jugada(symbol: String, letra:String): String{
    val scanner = Scanner(System.`in`)
    var x  = 0
    var y  = 0
    do {
        var bool = false
        print("Enter the coordinates: ")
        try {
            x = scanner.nextInt()
            y = scanner.nextInt()
            if (!(x in 1..3 && y in 1..3)) {
                bool = true
                println("Coordinates should be from 1 to 3!")
            } else if (symbol[(x - 1) * 3 + y - 1] != ' ') {
                bool = true
                println("This cell is occupied! Choose another one!")

            }
        } catch (e: InputMismatchException) {
            bool = true
            println("You should enter numbers!")
            scanner.nextLine()
        }
    } while (bool)
    return symbol.replaceRange((x - 1) * 3 + y - 1, (x - 1) * 3 + y, letra)
}
fun tablero(symbol: String) {
    println("---------")
    println("| ${symbol[0]} ${symbol[1]} ${symbol[2]} | ")
    println("| ${symbol[3]} ${symbol[4]} ${symbol[5]} | ")
    println("| ${symbol[6]} ${symbol[7]} ${symbol[8]} | ")
    println("---------")
}

fun getState(symbol: String): String {
    if (abs(symbol.count { c -> c == 'X' } - symbol.count { c -> c == 'O' }) > 1) {
        return "Impossible"
    }

    if (win(symbol, 'X') && win(symbol, 'O')) {
        return "Impossible"
    }

    if (win(symbol, 'X')) {
        return "X wins"
    }

    if (win(symbol, 'O')) {
        return "O wins"
    }

    if (!win(symbol, 'X') && !win(symbol, 'O') && !symbol.contains(" ")) {
        return "Draw"
    }

    return "Game not finished"
}

fun win(symbol: String, player: Char): Boolean {
    val game = arrayOf(
        arrayOf(symbol[0], symbol[1], symbol[2]),
        arrayOf(symbol[3], symbol[4], symbol[5]),
        arrayOf(symbol[6], symbol[7], symbol[8])
    )
    // Columnas
    for (i in 0..2) {
        if (game[i][0] == player && game[i][1] == player && game[i][2] == player) {
            return true
        }
    }
    // Filas
    for (i in 0..2) {
        if (game[0][i] == player && game[1][i] == player && game[2][i] == player) {
            return true
        }
    }
    // Diagonal ppal
    if (game[0][0] == player && game[1][1] == player && game[2][2] == player) {
        return true
    }
    // Diagonal secundaria
    if (game[0][2] == player && game[1][1] == player && game[2][0] == player) {
        return true
    }

    return false
}
