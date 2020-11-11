package com.stevenliebregt.feesboek.application.javalin.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import io.javalin.plugin.openapi.annotations.ContentType
import org.eclipse.jetty.server.Server
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.text.SimpleDateFormat

class AppConfig : KoinComponent {
    private val authConfig: AuthConfig by inject()
//    private val routerConfig: RouterConfig by inject() // TODO:

    fun setup(): Javalin {
        this.configureMapper()

        val port = System.getenv("PORT")?.toIntOrNull()
                ?: getKoin().getProperty("server_port", "7000").toInt()

        val app = Javalin.create { config ->
            config.apply {
                defaultContentType = ContentType.JSON

                contextPath = getKoin().getProperty("context_path", "/")

                server {
                    Server(port)
                }
            }
        }

        authConfig.configure(app)
//        routerConfig.configure(app) // TODO:

        return app
    }

    private fun configureMapper() {
        JavalinJackson.configure(
                jacksonObjectMapper()
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .setDateFormat(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
                        .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
        )
    }
}