package com.pt.chat.domain.useCase

import com.pt.chat.data.mapper.toEntity
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.model.User
import com.pt.common.utils.BaseUseCase

class SaveUsersUseCase(
    private val repository: IChatRepository
) : BaseUseCase<List<User>, Unit> {
    override suspend fun execute(params: List<User>) {
        val userEntities = params.map { it.toEntity() }
        repository.saveUsers(userEntities)
    }
}