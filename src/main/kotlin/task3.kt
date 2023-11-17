import java.util.*

// ШИФРВИЖЕНЕР
// КОД
// НЪРЁУЕЬЦЙЪВ
// ГЛМЫЕБСЗЁПУ

fun main() {
    val scanner = Scanner(System.`in`)

    // Ввод исходного сообщения и ключа
    print("Введите исходное сообщение: ")
    val originalMessage = scanner.nextLine().uppercase(Locale.getDefault())

    print("Введите ключ: ")
    val key = scanner.nextLine().uppercase(Locale.getDefault())

    // Выбор типовой таблицы или генерации случайной
    print("Выберите типовую таблицу (1) или случайную (2): ")
    val tableChoice = scanner.nextInt()

    val vigenereTable = if (tableChoice == 1) {
        createDefaultVigenereTable()
    } else {
        createRandomVigenereTable()
    }

    val keyLong = repeatKey(key, originalMessage.length)

    // Вывод исходного сообщения и ключа
    println("Текст: $originalMessage")
    println("Ключ:  $keyLong")

    // Шифрование и вывод зашифрованного сообщения и шифровальной таблицы
    val encryptedMessage = vigenereEncrypt(originalMessage, key, vigenereTable)
    println("Итог:  $encryptedMessage")

    // Расшифровка и вывод расшифрованного сообщения
    val decryptedMessage = vigenereDecrypt(encryptedMessage, key, vigenereTable)
    println("Расшифрованное сообщение: $decryptedMessage\n")

    println("Шифровальная таблица:")
    printVigenereTable(vigenereTable)
}

fun createDefaultVigenereTable(): Array<CharArray> {
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    val table = Array(33) { CharArray(33) }

    for (i in 0 until 33) {
        for (j in 0 until 33) {
            val index = (i + j) % 33
            table[i][j] = alphabet[index]
        }
    }

    return table
}

fun createRandomVigenereTable(): Array<CharArray> {
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    return Array(33) { alphabet.toCharArray().toMutableList().shuffled().toCharArray() }
}

fun vigenereEncrypt(message: String, key: String, table: Array<CharArray>): String {
    val encryptedMessage = StringBuilder()

    for (i in message.indices) {
        val messageChar = message[i]
        val keyChar = key[i % key.length]
        val row = table[0].indexOf(keyChar)
        val col = table[row].indexOf(messageChar)
        encryptedMessage.append(table[0][col])
    }

    return encryptedMessage.toString()
}

fun vigenereDecrypt(encryptedMessage: String, key: String, table: Array<CharArray>): String {
    val decryptedMessage = StringBuilder()

    for (i in encryptedMessage.indices) {
        val encryptedChar = encryptedMessage[i]
        val keyChar = key[i % key.length]
        val row = table[0].indexOf(keyChar)
        val col = table[row].indexOf(encryptedChar)
        decryptedMessage.append(table[0][col])
    }

    return decryptedMessage.toString()
}

fun printVigenereTable(table: Array<CharArray>) {

    for (i in 0 until 33) {
        for (j in 0 until 33) {
            print("${table[i][j]} ")
        }
        println()
    }
}

fun repeatKey(key: String, length: Int): String {
    val repeatedKey = StringBuilder()

    while (repeatedKey.length < length) {
        repeatedKey.append(key)
    }

    return repeatedKey.substring(0, length)
}