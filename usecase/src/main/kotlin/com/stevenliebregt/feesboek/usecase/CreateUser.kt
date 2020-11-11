package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.common.jwt.JwtProvider
import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.UserRepository

class CreateUser(private val userRepository: UserRepository, private val jwtProvider: JwtProvider) {
    fun create(user: User): User {
        if (userRepository.findByEmail(user.email) != null) throw Exception("User already exists") // TODO: Custom exception

        val userToSave = User.Builder()
                .email(user.email)
                .password(user.password)
                .build()

        return userRepository.add(userToSave)
    }
}