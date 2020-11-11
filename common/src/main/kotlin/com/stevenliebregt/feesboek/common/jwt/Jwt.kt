package com.stevenliebregt.feesboek.common.jwt

import com.auth0.jwt.interfaces.Claim

data class Jwt(
        val subject: String,
        val claims: Map<String, Claim>
)