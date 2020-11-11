package com.stevenliebregt.feesboek.application.javalin.config

import com.stevenliebregt.feesboek.application.javalin.util.jwt.JwtProvider
import org.koin.dsl.bind
import org.koin.dsl.module

object ModulesConfig {
    private val configModule = module {
        single { AppConfig() }
        single { JwtProvider() }
        single { AuthConfig(get()) }
        single { RouterConfig() }
        single {
            DbConfig(
                    getProperty("db.jdbc_url"),
                    getProperty("db.username"),
                    getProperty("db.password")
            ).dataSource
        }
    }

    private val userModule = module {

    }

    private val postModule = module {
//        single { PostRouter(get()) } bind IRouter::class
//        single { PostController() }
    }

    val allModules = listOf(
            configModule,
            userModule,
            postModule
    )
}