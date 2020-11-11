package com.stevenliebregt.feesboek.application.javalin.web.endpoint

import com.stevenliebregt.feesboek.application.javalin.config.Roles
import com.stevenliebregt.feesboek.application.javalin.web.controller.UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.SecurityUtil.roles

class UserEndpoint(private val userController: UserController) : Endpoint {
    override fun register(app: Javalin) {
        app.routes {
            path("users") {
                post(userController::createUser, roles(Roles.ANYONE))
            }
        }
    }
}