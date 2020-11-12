package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.usecase.repository.UserRepository

class LogoutUseCase(private val userRepository: UserRepository) {
    fun logout(email: String) {
        val user = userRepository.findByEmail(email) ?: throw Exception("No user with that email") // TODO: custom exception

        user.token = null
        userRepository.update(user)
    }
}