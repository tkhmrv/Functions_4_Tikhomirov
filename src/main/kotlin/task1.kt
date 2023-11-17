import kotlin.random.Random

fun main() {
    println("Добро пожаловать в игру Камень-Ножницы-Бумага!")
    println("Выберите свой ход: 1 - Камень, 2 - Ножницы, 3 - Бумага")

    val playerChoice = readlnOrNull()?.toIntOrNull()

    if (playerChoice != null && playerChoice in 1..3) {
        val computerChoice = Random.nextInt(1, 4)
        displayChoices(playerChoice, computerChoice)

        val result = determineWinner(playerChoice, computerChoice)
        println(result)
    } else {
        println("Некорректный ввод. Пожалуйста, выберите число от 1 до 3.")
    }
}

fun displayChoices(player: Int, computer: Int) {
    println("Ваш выбор: ${translateChoice(player)}")
    println("Выбор компьютера: ${translateChoice(computer)}")
}

fun translateChoice(choice: Int): String {
    return when (choice) {
        1 -> "Камень"
        2 -> "Ножницы"
        3 -> "Бумага"
        else -> "Неизвестный выбор"
    }
}

fun determineWinner(player: Int, computer: Int): String {
    return when {
        player == computer -> "Ничья! Переиграйте еще раз."
        (player == 1 && computer == 3) || (player == 2 && computer == 1) || (player == 3 && computer == 2) ->
            "Вы победили! Поздравляю!"

        else -> "Вы проиграли. Попробуйте еще раз."
    }
}