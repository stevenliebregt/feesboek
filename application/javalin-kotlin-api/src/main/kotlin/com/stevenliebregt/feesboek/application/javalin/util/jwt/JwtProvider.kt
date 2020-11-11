package com.stevenliebregt.feesboek.application.javalin.util.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.stevenliebregt.feesboek.domain.entity.User
import io.javalin.core.security.Role
import java.util.*

class JwtProvider {
    fun decodeJWT(token: String): DecodedJWT = JWT.require(Cipher.algorithm).build().verify(token)

    fun createJWT(user: User, role: Role): String? =
            JWT.create()
                    .withIssuedAt(Date())
                    .withSubject(user.email)
                    .withClaim("role", role.toString())
                    .withExpiresAt(Date(System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000))
                    .sign(Cipher.algorithm)
}