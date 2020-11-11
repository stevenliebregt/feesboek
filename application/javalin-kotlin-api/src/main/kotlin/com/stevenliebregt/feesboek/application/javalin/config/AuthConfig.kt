package com.stevenliebregt.feesboek.application.javalin.config

import com.stevenliebregt.feesboek.common.jwt.JwtProvider
import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse

internal enum class Roles : Role {
    ANYONE,
    AUTHENTICATED
}

class AuthConfig(private val jwtProvider: JwtProvider) {
    fun configure(app: Javalin) {
        app.config.accessManager { handler, ctx, permittedRoles ->
            try {
                val token = jwtProvider.decodeJWT(getHeaderJwtToken(ctx) ?: throw ForbiddenResponse())

                val email = token.subject
                val role = token.claims["role"]?.toString()?.let { Roles.valueOf(it) } ?: Roles.ANYONE

                permittedRoles.takeIf { !it.contains(role) }?.apply { throw ForbiddenResponse() }
                ctx.attribute("email", email)

                handler.handle(ctx)
            } catch (exception: Exception) {
                throw ForbiddenResponse("Invalid token")
            }
        }
    }

    private fun getHeaderJwtToken(ctx: Context): String? = ctx.header(AUTHORIZATION_HEADER_NAME)
            ?.substringAfter("Token")
            ?.trim()

    companion object {
        private const val AUTHORIZATION_HEADER_NAME = "Authorization"
    }
}