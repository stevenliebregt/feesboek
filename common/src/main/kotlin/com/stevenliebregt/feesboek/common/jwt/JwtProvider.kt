package com.stevenliebregt.feesboek.common.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import java.util.*

class JwtProvider {
    private val verifier: JWTVerifier by lazy { JWT.require(Cipher.algorithm).build() }

    fun decodeJWT(token: String): Jwt {
        val decoded = verifier.verify(token)

        return Jwt(decoded.subject, decoded.claims)
    }

    fun createJWT(subject: String, claims: Map<String, String>): String? =
            JWT.create()
                    .withIssuedAt(Date())
                    .withSubject(subject)
                    .apply { claims.forEach { withClaim(it.key, it.value) } }
                    .withExpiresAt(Date(System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000))
                    .sign(Cipher.algorithm)
}