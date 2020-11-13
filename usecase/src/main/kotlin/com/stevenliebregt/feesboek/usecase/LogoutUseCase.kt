package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.usecase.exception.InvalidCredentialsException
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository

class LogoutUseCase(private val userRepository: IUserRepository) {
    fun logout(email: String) {
        val user = userRepository.findByEmail(email) ?: throw InvalidCredentialsException()

        user.token = null
        userRepository.update(user)
    }
}