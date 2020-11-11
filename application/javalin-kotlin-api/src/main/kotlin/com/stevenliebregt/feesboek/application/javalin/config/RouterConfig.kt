package com.stevenliebregt.feesboek.application.javalin.config

import com.stevenliebregt.feesboek.application.javalin.web.router.IRouter
import io.javalin.Javalin
import org.koin.core.KoinComponent

class RouterConfig : KoinComponent {
    fun configure(app: Javalin) {
        getKoin()
                .getAll<IRouter>()
                .forEach { it.register(app) }
    }
}