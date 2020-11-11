package com.stevenliebregt.feesboek.application.javalin.web.controller

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.CreateUser
import io.javalin.http.Context

class UserController(private val createUser: CreateUser) {
    fun createUser(ctx: Context) {
        val body = ctx.bodyValidator<CreateUserBody>()
                .check({ !it.email.isNullOrBlank() }, "Email cannot be empty")
                .check({ !it.password.isNullOrBlank() }, "Password cannot be empty")
                .get()

        val user = createUser.create(body.toUser())

        ctx.json(user)
    }

    private data class CreateUserBody(val email: String?, val password: String?) {
        fun toUser(): User = User.Builder()
                .email(email!!)
                .password(password!!)
                .build()
    }
}