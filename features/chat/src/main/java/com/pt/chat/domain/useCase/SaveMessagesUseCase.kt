package com.pt.chat.domain.useCase

import com.pt.chat.data.mapper.toEntity
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.model.Message
import com.pt.common.utils.BaseUseCase

class SaveMessagesUseCase(
    private val repository: IChatRepository
) : BaseUseCase<List<Message>, Unit> {
    override suspend fun execute(params: List<Message>) {
        val messageEntities = params.map { it.toEntity() }
        repository.saveMessages(messageEntities)
    }
}
