package com.stevenliebregt.feesboek.usecase.repository

import com.stevenliebregt.feesboek.domain.entity.User

interface IUserRepository : IIntIdRepository<User> {
    fun findByEmail(email: String): User?
}