package com.stevenliebregt.feesboek.application.javalin.web.controller

import com.stevenliebregt.feesboek.usecase.LoginUseCase
import com.stevenliebregt.feesboek.usecase.LogoutUseCase
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse

class TokenController(private val loginUseCase: LoginUseCase, private val logoutUseCase: LogoutUseCase) {
    fun createToken(ctx: Context) {
        val body = ctx.bodyValidator<CreateTokenBody>()
                .check({ !it.email.isNullOrBlank() }, "Email cannot be empty")
                .check({ !it.password.isNullOrBlank() }, "Password cannot be empty")
                .get()

        val token = loginUseCase.login(body.email!!, body.password!!) // TODO: There should be a better way than force unwrapping

        ctx.status(200).json(mapOf("token" to token))
    }

    fun removeToken(ctx: Context) {
        val email: String = ctx.attribute("email") ?: throw ForbiddenResponse()

        logoutUseCase.logout(email)

        ctx.status(200)
    }

    private data class CreateTokenBody(val email: String?, val password: String?)
}