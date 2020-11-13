package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.domain.entity.Post
import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.IPostRepository
import org.joda.time.DateTime

class CreatePostUseCase(private val postRepository: IPostRepository, private val findUserUseCase: FindUserUseCase) {
    fun create(post: Post, userId: Int): Post {
        // TODO: Validate input

        post.createdAt = DateTime.now()
        post.author = findUserUseCase.findById(userId)!!

        return postRepository.add(post)
    }
}