package com.pt.chat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageList(
    val messages: List<Message>,
    val users: List<User>,
    val attachments: List<Attachment>?
)
