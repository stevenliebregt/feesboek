package com.stevenliebregt.feesboek.application.javalin.config

import com.stevenliebregt.feesboek.common.jwt.JwtProvider
import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse
import java.lang.Exception

internal enum class Roles : Role {
    ANYONE,
    AUTHENTICATED
}

class AuthConfig(private val jwtProvider: JwtProvider) {
    fun configure(app: Javalin) {
        app.config.accessManager { handler, ctx, permittedRoles ->
            val authorizationHeaderContent = getHeaderJwtToken(ctx)

            if (authorizationHeaderContent == null) {
                if (permittedRoles.contains(Roles.AUTHENTICATED)) throw ForbiddenResponse("No authorization header")
            } else {
                try {
                    val token = jwtProvider.decodeJWT(authorizationHeaderContent)

                    val email = token.subject
                    val role = token.getClaim("role")?.let { Roles.valueOf(it) } ?: Roles.ANYONE

                    permittedRoles.takeIf { !it.contains(role) }?.apply { throw ForbiddenResponse("Not enough rights") }
                    ctx.attribute("email", email)
                } catch (exception: Exception) { // Failed to decode token
                    throw ForbiddenResponse("Invalid token")
                }
            }

            handler.handle(ctx)
        }
    }

    private fun getHeaderJwtToken(ctx: Context): String? = ctx.header(AUTHORIZATION_HEADER_NAME)
            ?.substringAfter("Bearer")
            ?.trim()

    companion object {
        private const val AUTHORIZATION_HEADER_NAME = "Authorization"
    }
}