package com.stevenliebregt.feesboek.application.javalin.web.endpoint

import com.stevenliebregt.feesboek.application.javalin.config.Roles
import com.stevenliebregt.feesboek.application.javalin.web.controller.PostController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.SecurityUtil.roles

class PostEndpoint(private val postController: PostController) : Endpoint {
    override fun register(app: Javalin) {
        app.routes {
            path("posts") {
                get(postController::getPosts, roles(Roles.AUTHENTICATED))

                path(":id") {
                    get(postController::getPostById, roles(Roles.AUTHENTICATED))
                }
            }
        }
    }
}