package wallMessage

import service.MessageService

fun main() {

    val service = MessageService
    service.chatAdd(Chat(chatName = "Sasha"))
    service.chatAdd(Chat(chatName = "Nastya"))
    service.chatAdd(Chat(chatName = "Nika"))
    service.chatAdd(Chat(chatName = "Masha"))


    service.messageAdd(1, Message(messageText = "qqq", markerIn = true, read = true))
    service.messageAdd(1, Message(messageText = "www", markerIn = true, messageLike = 2))
    service.messageAdd(2, Message(messageText = "eee", markerIn = true, messageLike = 3))
    service.messageAdd(2, Message(messageText = "yyy", markerIn = false, read = true))
    service.messageAdd(4, Message(messageText = "rrr", markerIn = false, messageLike = 5))
    service.messageAdd(4, Message(messageText = "ttt", markerIn = false, read = true, messageLike = 6))

    println(service.messageLikeCount())
//    println(service.messageGet())
//    println(service.chatGet())
//    println()

//    service.chatDelete(1)
//    println(service.messageGet())
//    println(service.chatGet())
//    println()
//    println(service.messagesCountChat(2))
//    println(service.messangesCountIn(3))
//    println(service.messangesCountOut(2))
//    println()
//
    val nums = listOf(1, 2, 3, 4, 5).asSequence()
    val sum = nums
        .filter { it > 2 }
        .sum()

    println(sum)
//    val count = nums.count { it % 2 == 1 }
//    println(count)
//    println(nums.filter { it % 2 == 0 && it > 10}.minOrNull())
//
//    val s = " "
//    val t = " "
//    println(s == t)
//
////    val n : Message?
//    val n : Message? = Message(messageText = "aaa ", markerIn = true)
//    println(n!!.messageText)
//    println(n?.messageText)
////println(n.messageText)
//    println()
//    val a : Collection<Int>
//    infix fun Message.vvvv(v: Int) {
//        println(v)
//    }
//
//    n.vvvv(22)
//
//    n vvvv 22
}