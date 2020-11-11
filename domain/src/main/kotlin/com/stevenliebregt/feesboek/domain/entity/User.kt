package com.stevenliebregt.feesboek.domain.entity

data class User(
        private val id: Int,
        val email: String,
        val password: String,
        val token: String,
        val firstname: String,
        val lastname: String
)