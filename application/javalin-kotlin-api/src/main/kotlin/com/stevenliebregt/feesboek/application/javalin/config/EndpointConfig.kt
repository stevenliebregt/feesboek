package com.stevenliebregt.feesboek.application.javalin.config

import com.stevenliebregt.feesboek.application.javalin.web.endpoint.Endpoint
import io.javalin.Javalin
import org.koin.core.KoinComponent

class EndpointConfig : KoinComponent {
    fun configure(app: Javalin) {
        // Find all registered endpoint classes in the container
        getKoin()
                .getAll<Endpoint>()
                .forEach { it.register(app) }
    }
}