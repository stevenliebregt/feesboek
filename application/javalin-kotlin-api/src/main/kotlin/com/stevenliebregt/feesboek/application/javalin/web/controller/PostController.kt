package com.stevenliebregt.feesboek.application.javalin.web.controller

import com.stevenliebregt.feesboek.domain.entity.Post
import com.stevenliebregt.feesboek.usecase.CreatePostUseCase
import com.stevenliebregt.feesboek.usecase.FindPostUseCase
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse

class PostController(private val findPostUseCase: FindPostUseCase, private val createPostUseCase: CreatePostUseCase) {
    fun getPosts(ctx: Context) {
        ctx.json(findPostUseCase.findAll())
    }

    fun getPostById(ctx: Context) {
        val id = ctx.pathParam("id").toInt()

        ctx.json(findPostUseCase.findById(id) ?: throw BadRequestResponse("No post with id: $id found"))
    }

    fun createPost(ctx: Context) {
        val body = ctx.bodyValidator<CreatePostBody>()
            .check({ !it.title.isNullOrBlank() }, "Title cannot be empty")
            .check({ it.title?.let { title -> title.length > 8 } ?: false })
            .check({ !it.body.isNullOrBlank() }, "Body cannot be empty")
            .get()

        val post = createPostUseCase.create(body.toPost(), ctx.attribute<String>("email") ?: throw ForbiddenResponse())

        ctx.status(200).json(post)
    }

    fun updatePost(ctx: Context) {
        TODO("Implement this")
    }

    fun deletePost(ctx: Context) {
        TODO("Implement this")
    }

    private data class CreatePostBody(val title: String?, val body: String?) {
        fun toPost() = Post.Builder()
            .title(title!!)
            .body(body!!)
            .build()
    }
}