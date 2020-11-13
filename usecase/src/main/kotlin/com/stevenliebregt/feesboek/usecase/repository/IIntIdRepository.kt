package com.stevenliebregt.feesboek.usecase.repository

interface IIntIdRepository <T> : IRepository<T> {
    fun findById(id: Int): T?
}