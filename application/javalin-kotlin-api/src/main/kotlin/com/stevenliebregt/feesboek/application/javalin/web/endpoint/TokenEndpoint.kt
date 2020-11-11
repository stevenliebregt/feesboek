package com.stevenliebregt.feesboek.application.javalin.web.endpoint

import com.stevenliebregt.feesboek.application.javalin.config.Roles
import com.stevenliebregt.feesboek.application.javalin.web.controller.TokenController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.SecurityUtil.roles

class TokenEndpoint(private val tokenController: TokenController) : Endpoint {
    override fun register(app: Javalin) {
        app.routes {
            path("tokens") {
                post(tokenController::createToken, roles(Roles.ANYONE))

                delete(tokenController::removeToken, roles(Roles.AUTHENTICATED))
            }
        }
    }
}