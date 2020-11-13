package com.stevenliebregt.feesboek.application.javalin.web.endpoint

import com.stevenliebregt.feesboek.application.javalin.config.Roles
import com.stevenliebregt.feesboek.application.javalin.web.controller.PostController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.SecurityUtil.roles

class PostEndpoint(private val postController: PostController) : Endpoint {
    override fun register(app: Javalin) {
        val requiredRoles = roles(Roles.AUTHENTICATED)

        app.routes {
            path("posts") {
                get(postController::getPosts, requiredRoles)

                post(postController::createPost, requiredRoles)

                path(":id") {
                    get(postController::getPostById, requiredRoles)

                    put(postController::updatePost, requiredRoles)

                    delete(postController::updatePost, requiredRoles)
                }
            }
        }
    }
}