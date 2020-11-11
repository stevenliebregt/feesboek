package com.stevenliebregt.feesboek.usecase.repository

import com.stevenliebregt.feesboek.domain.entity.User

interface UserRepository : IRepository<User> {
    fun findByEmail(email: String): User?
}