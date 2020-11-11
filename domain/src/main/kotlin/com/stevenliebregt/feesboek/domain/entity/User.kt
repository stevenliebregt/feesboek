package com.stevenliebregt.feesboek.domain.entity

data class User(
        private val id: String,
        private val email: String,
        private val password: String
)