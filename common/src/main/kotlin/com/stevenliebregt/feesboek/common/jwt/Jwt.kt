package com.stevenliebregt.feesboek.common.jwt

import com.auth0.jwt.interfaces.Claim

data class Jwt(
        val subject: String,
        private val claims: Map<String, Claim>
) {
    fun getClaim(key: String): String? = claims[key]?.asString() // TODO: This could also be another type, Int, Boolean, etc
}