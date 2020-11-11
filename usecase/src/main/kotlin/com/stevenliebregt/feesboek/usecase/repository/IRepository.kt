package com.stevenliebregt.feesboek.usecase.repository

interface IRepository <T> {
    fun all(): List<T>

    fun add(entity: T): T

    fun update(entity: T): T
}