package com.stevenliebregt.feesboek.application.javalin.web.router

import io.javalin.Javalin

interface IRouter {
    fun register(app: Javalin)
}