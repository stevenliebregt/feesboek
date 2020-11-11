package com.stevenliebregt.feesboek.usecase.repository

import com.stevenliebregt.feesboek.domain.entity.User

interface UserRepository {
    fun findAllUsers(): List<User>
}