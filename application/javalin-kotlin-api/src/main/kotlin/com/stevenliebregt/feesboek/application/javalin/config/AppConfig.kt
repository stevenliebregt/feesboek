package com.stevenliebregt.feesboek.application.javalin.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.stevenliebregt.feesboek.data.exposed.ExposedSetup
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import io.javalin.plugin.openapi.annotations.ContentType
import org.eclipse.jetty.server.Server
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.text.SimpleDateFormat

class AppConfig : KoinComponent {
    private val exposedSetup: ExposedSetup by inject() // TODO: Not the nicest place, maybe an interface for "before app start" things
    private val authConfig: AuthConfig by inject()
    private val endpointConfig: EndpointConfig by inject()

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

        exposedSetup.setup() // TODO: Not the nicest place, maybe an interface for "before app start" things

        authConfig.configure(app)
        endpointConfig.configure(app)

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