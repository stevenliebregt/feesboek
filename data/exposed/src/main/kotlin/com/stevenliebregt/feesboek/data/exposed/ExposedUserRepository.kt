package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.UserRepository
import javax.sql.DataSource

class ExposedUserRepository(private val dataSource: DataSource) : UserRepository {
    override fun findAllUsers(): List<User> {
        TODO("Not yet implemented")
    }
}