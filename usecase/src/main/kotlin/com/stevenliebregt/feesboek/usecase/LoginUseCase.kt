package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.common.jwt.JwtProvider
import com.stevenliebregt.feesboek.usecase.exception.InvalidCredentialsException
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository

class LoginUseCase(private val userRepository: IUserRepository, private val jwtProvider: JwtProvider) {
    fun login(email: String, password: String): String {
        val user = userRepository.findByEmail(email) ?: throw InvalidCredentialsException()

        if (user.password != password) throw InvalidCredentialsException()

        user.token = jwtProvider.createJWT(user.id.toString(), mapOf("role" to "AUTHENTICATED")) // TODO: role is hidden in application package, fix this
        userRepository.update(user)

        return user.token ?: throw InvalidCredentialsException()
    }
}