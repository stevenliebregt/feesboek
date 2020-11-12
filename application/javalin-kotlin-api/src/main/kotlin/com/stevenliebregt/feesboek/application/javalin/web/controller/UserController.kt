package com.stevenliebregt.feesboek.application.javalin.web.controller

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.CreateUserUseCase
import io.javalin.http.Context

class UserController(private val createUserUseCase: CreateUserUseCase) {
    fun createUser(ctx: Context) {
        val body = ctx.bodyValidator<CreateUserBody>()
                .check({ !it.email.isNullOrBlank() }, "Email cannot be empty")
                .check({ !it.password.isNullOrBlank() }, "Password cannot be empty")
                .get()

        val user = createUserUseCase.create(body.toUser())

        ctx.status(200).json(user)
    }

    private data class CreateUserBody(val email: String?, val password: String?) {
        fun toUser(): User = User.Builder()
                .email(email!!)
                .password(password!!)
                .build()
    }
}