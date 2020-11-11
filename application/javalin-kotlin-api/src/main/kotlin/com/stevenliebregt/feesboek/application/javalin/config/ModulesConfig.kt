package com.stevenliebregt.feesboek.application.javalin.config

import com.stevenliebregt.feesboek.application.javalin.web.controller.TokenController
import com.stevenliebregt.feesboek.application.javalin.web.controller.UserController
import com.stevenliebregt.feesboek.application.javalin.web.endpoint.Endpoint
import com.stevenliebregt.feesboek.application.javalin.web.endpoint.TokenEndpoint
import com.stevenliebregt.feesboek.application.javalin.web.endpoint.UserEndpoint
import com.stevenliebregt.feesboek.common.jwt.JwtProvider
import com.stevenliebregt.feesboek.data.exposed.ExposedUserRepository
import com.stevenliebregt.feesboek.usecase.CreateUser
import com.stevenliebregt.feesboek.usecase.repository.UserRepository
import org.koin.dsl.bind
import org.koin.dsl.module

object ModulesConfig {
    private val configModule = module {
        single { AppConfig() }
        single { JwtProvider() }
        single { AuthConfig(get()) }
        single { EndpointConfig() }
        single {
            DbConfig(
                    getProperty("db.jdbc_url"),
                    getProperty("db.username"),
                    getProperty("db.password")
            ).dataSource
        }
    }

    private val tokenModule = module {
        single { TokenEndpoint(get()) } bind Endpoint::class
        single { TokenController() }
    }

    private val userModule = module {
        single { UserEndpoint(get()) } bind Endpoint::class
        single { UserController(get()) }
    }

    private val postModule = module {
//        single { PostEndpoint(get()) } bind Endpoint::class
//        single { PostController() }
    }

    private val repositoryModule = module {
        single<UserRepository> { ExposedUserRepository(get()) }
    }

    private val useCaseModule = module {
        single { CreateUser(get(), get()) }
    }

    val allModules = listOf(
            configModule,
            tokenModule,
            userModule,
            postModule,
            repositoryModule,
            useCaseModule
    )
}