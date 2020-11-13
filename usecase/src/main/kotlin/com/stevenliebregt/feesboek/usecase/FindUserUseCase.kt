package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository

class FindUserUseCase(private val userRepository: IUserRepository) {
    fun findById(id: Int): User? = userRepository.findById(id)
}