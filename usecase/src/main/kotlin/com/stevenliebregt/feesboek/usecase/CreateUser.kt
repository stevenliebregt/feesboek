package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.domain.entity.User

class CreateUser {
    fun create(user: User): User {
        return user
    }
}