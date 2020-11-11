package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.UserRepository

class CreateUser(private val userRepository: UserRepository) {
    fun create(user: User): User {
        return user
    }
}