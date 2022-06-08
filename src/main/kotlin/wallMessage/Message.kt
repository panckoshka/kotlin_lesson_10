package wallMessage

//сообщения
data class Message(
    val chatId: Int = 0,
    val messageId: Int = 0,
    var messageText: String,
    val markerIn: Boolean,      // true - входящие, false - исходящие
    var read: Boolean = false,   // не прочитанные
    var messageLike: Int = 0
) {
}