package com.stevenliebregt.feesboek.application.javalin.web.endpoint

import io.javalin.Javalin

interface Endpoint {
    fun register(app: Javalin)
}