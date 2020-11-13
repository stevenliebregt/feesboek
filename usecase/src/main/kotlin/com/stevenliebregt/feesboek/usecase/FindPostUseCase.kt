package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.domain.entity.Post
import com.stevenliebregt.feesboek.usecase.repository.IPostRepository

class FindPostUseCase(private val postRepository: IPostRepository) {
    fun findAll(): List<Post> = postRepository.all()

    fun findById(id: Int): Post? = postRepository.findById(id)
}