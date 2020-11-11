package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.UserRepository

class ExposedUserRepository : UserRepository {
    override fun findAllUsers(): List<User> {
        TODO("Not yet implemented")
    }
}