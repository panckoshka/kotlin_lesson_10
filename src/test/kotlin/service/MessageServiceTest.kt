package service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import wallMessage.Chat
import wallMessage.ChatNotFoundException
import wallMessage.Message
import wallMessage.MessegeNotFoundException
import kotlin.test.assertFailsWith

internal class MessageServiceTest {
    @BeforeEach
    fun cleanUp() {
        val service = MessageService
        service.chatGet().clear()
        service.messageGet().clear()
        service.chatGlobalId = 0
        service.messageIdGlobal = 0
    }

    @Test
    fun chatAddMessageAddMessagesCountChatFul() {
        val service = MessageService

        service.chatAdd(Chat(chatName = "Sasha"))
        service.chatAdd(Chat(chatName = "Nastya"))
        service.chatAdd(Chat(chatName = "Nika"))
        service.chatAdd(Chat(chatName = "Masha"))

        service.messageAdd(1, Message(messageText = "qqq", markerIn = true, read = true))
        service.messageAdd(1, Message(messageText = "www", markerIn = true))
        service.messageAdd(2, Message(messageText = "eee", markerIn = true))
        service.messageAdd(2, Message(messageText = "yyy", markerIn = false, read = true))
        service.messageAdd(4, Message(messageText = "rrr", markerIn = false))
        service.messageAdd(4, Message(messageText = "ttt", markerIn = false, read = true))

        service.messagesCountChat(1)

        assertEquals("Sasha", service.chatGet()[0].chatName)
        assertEquals("qqq", service.messageGet()[0].messageText)
        assertEquals(2, service.messagesCountChat(2))
        assertEquals(1, service.messangesCountIn(2))
        assertEquals(2, service.messangesCountOut(4))

    }

    @Test
    fun messageChatDelete(){
        val service = MessageService

        service.chatAdd(Chat(chatName = "Sasha"))
        service.chatAdd(Chat(chatName = "Nastya"))
        service.chatAdd(Chat(chatName = "Nika"))
        service.chatAdd(Chat(chatName = "Masha"))

        service.messageAdd(1, Message(messageText = "qqq", markerIn = true, read = true))
        service.messageAdd(1, Message(messageText = "www", markerIn = true))
        service.messageAdd(2, Message(messageText = "eee", markerIn = true))
        service.messageAdd(2, Message(messageText = "yyy", markerIn = false, read = true))
        service.messageAdd(4, Message(messageText = "rrr", markerIn = false))
        service.messageAdd(4, Message(messageText = "ttt", markerIn = false, read = true))

        service.chatDelete(1)
        service.messageDelete(4)
        assertEquals(3, service.chatGet().size)
        assertEquals(3,service.messageGet().size)

        assertFailsWith<ChatNotFoundException> { service.chatDelete(22) }
        assertFailsWith<MessegeNotFoundException> { service.messageDelete(33) }
    }

    @Test
    fun  messageEditchatEdit(){
        val service = MessageService

        service.chatAdd(Chat(chatName = "Sasha"))
        service.chatAdd(Chat(chatName = "Nastya"))
        service.chatAdd(Chat(chatName = "Nika"))
        service.chatAdd(Chat(chatName = "Masha"))

        service.messageAdd(1, Message(messageText = "qqq", markerIn = true, read = true))
        service.messageAdd(1, Message(messageText = "www", markerIn = true))
        service.messageAdd(2, Message(messageText = "eee", markerIn = true))
        service.messageAdd(2, Message(messageText = "yyy", markerIn = false, read = true))
        service.messageAdd(4, Message(messageText = "rrr", markerIn = false))
        service.messageAdd(4, Message(messageText = "ttt", markerIn = false, read = true))

        service.chatEdit(4, "textEdit1")
        service.messageEdit(1, "textEdit5")

        assertEquals("textEdit1", service.chatGet()[3].chatName)
        assertEquals("textEdit5", service.messageGet()[0].messageText)

        assertFailsWith<ChatNotFoundException> { service.chatEdit(33, " ") }
        assertFailsWith<MessegeNotFoundException> { service.messageEdit(44, " ") }

    }
}