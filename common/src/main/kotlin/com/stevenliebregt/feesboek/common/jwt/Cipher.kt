package com.stevenliebregt.feesboek.common.jwt

import com.auth0.jwt.algorithms.Algorithm

object Cipher {
    val algorithm: Algorithm = Algorithm.HMAC256("something-very-secret-here") // TODO: This secret should be injected from a config file

    fun encrypt(data: String?): ByteArray =
            algorithm.sign(data?.toByteArray())

}