package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.common.jwt.JwtProvider
import com.stevenliebregt.feesboek.usecase.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository, private val jwtProvider: JwtProvider) {
    fun login(email: String, password: String): String {
        val user = userRepository.findByEmail(email) ?: throw Exception("No user with that email") // TODO: custom exception

        if (user.password != password) throw Exception("Invalid password") // TODO: custom exception

        user.token = jwtProvider.createJWT(user.email, mapOf("role" to "AUTHENTICATED")) // TODO: role is hidden in application package, fix this
        userRepository.update(user)

        return user.token ?: throw Exception("Failed to create token")
    }
}