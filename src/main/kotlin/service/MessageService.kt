package service

import wallMessage.Message
import wallMessage.Chat
import wallMessage.ChatNotFoundException
import wallMessage.MessegeNotFoundException

object MessageService {
    var chatGlobalId = 0
    var messageIdGlobal = 0

    private val listChat = mutableListOf<Chat>()
    private val listChatDelete = mutableListOf<Chat>()
    private val listMessage = mutableListOf<Message>()
    private val listMessageDelete = mutableListOf<Message>()

    //    1. добавили чат
    fun chatAdd(chat: Chat) {
        chatGlobalId++
        val chatCopy = chat.copy(chatId = chatGlobalId)
        listChat.add(chatCopy)
    }

    //      2.добавили message
    fun messageAdd(chatId: Int, message: Message) {
        messageIdGlobal++
        val messageCopy = message.copy(messageId = messageIdGlobal, chatId = chatId)
        listMessage.add(messageCopy)
    }

    //      3. получили список чат
    fun chatGet(): MutableList<Chat> {
        return listChat
    }

    //      4.получили список massege
    fun messageGet(): MutableList<Message> {
        return listMessage
    }

    //      5. удаление message

    fun messageDelete(messageId: Int) {

        val messagerListCopy = listMessage.filter { it.messageId == messageId }
        if (messagerListCopy.size > 0) {
            listMessageDelete.addAll(messagerListCopy)
            listMessage.removeAll(messagerListCopy)
            return
        }
        throw MessegeNotFoundException()
    }

    //6. удаление чат
    fun chatDelete(chatId: Int) {
        val filterChatList = listChat.filter { it.chatId == chatId }
        if (filterChatList.size > 0) {
            val filterMessageList = listMessage.filter { it.chatId == chatId }

            listMessageDelete.addAll(filterMessageList)
            listMessage.removeAll { it.chatId == chatId }
            listChatDelete.addAll(filterChatList)
            listChat.removeAll { it.chatId == chatId }
            return
        }
        throw ChatNotFoundException()
    }

    //    7. изменить message
    fun messageEdit(messageId: Int, messageText: String) {
        val messageEditCopy = listMessage.filter { it.messageId == messageId }
        if (messageEditCopy.size > 0) {
            messageEditCopy[0].messageText = messageText
            return
        }
        throw MessegeNotFoundException()
    }

    //    8. изменить чат
    fun chatEdit(chatId: Int, chatName: String) {
        val chatEditCopy = listChat.filter { it.chatId == chatId }
        if (chatEditCopy.size > 0) {
            chatEditCopy[0].chatName = chatName
            return
        }
        throw ChatNotFoundException()
    }

    //    9. считать все messages
    val messagesCountChat = fun(chatId: Int): Int = listMessage.filter { it.chatId == chatId }.size

    //    10. считать входяшие messages, markerIn
    fun messangesCountIn(chatId: Int): Int {
        val messagesCoun = listMessage.filter {
            it.chatId == chatId && it.markerIn
        }
        return messagesCoun.size
    }

    // исходящие
    fun messangesCountOut(chatId: Int) = listMessage.filter {
        it.chatId == chatId && !it.markerIn
    }.size

    //    11. считать прочитанные
    fun messageReader(chatId: Int) = listMessage.filter {
        it.chatId == chatId && it.read
    }.size

    //    12. не прочитанные message
    fun messageUnReader(chatId: Int) = listMessage.filter {
        it.chatId == chatId && !it.read
    }.size

}



