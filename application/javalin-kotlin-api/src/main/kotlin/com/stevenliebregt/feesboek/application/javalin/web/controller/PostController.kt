package com.stevenliebregt.feesboek.application.javalin.web.controller

import com.stevenliebregt.feesboek.usecase.FindPostUseCase
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context

class PostController(private val findPostUseCase: FindPostUseCase) {
    fun getPosts(ctx: Context) {
        ctx.json(findPostUseCase.findAll())
    }

    fun getPostById(ctx: Context) {
        val id = ctx.pathParam("id").toInt()

        ctx.json(findPostUseCase.findById(id) ?: throw BadRequestResponse("No post with id: $id found"))
    }
}