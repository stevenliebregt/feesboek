package com.stevenliebregt.feesboek.domain.entity

data class User(
        private val id: Int,
        val email: String,
        val password: String,
        val token: String?
) {
    class Builder {
        private var email: String = ""
        private var password: String = ""
        private var token: String? = null

        fun email(email: String) = apply { this.email = email }
        fun password(password: String) = apply { this.password = password }
        fun token(token: String?) = apply { this.token = token }

        fun build() = User(-1, email, password, null)
    }
}