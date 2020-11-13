package com.stevenliebregt.feesboek.application.javalin.config

import com.stevenliebregt.feesboek.application.javalin.web.controller.PostController
import com.stevenliebregt.feesboek.application.javalin.web.controller.TokenController
import com.stevenliebregt.feesboek.application.javalin.web.controller.UserController
import com.stevenliebregt.feesboek.application.javalin.web.endpoint.Endpoint
import com.stevenliebregt.feesboek.application.javalin.web.endpoint.PostEndpoint
import com.stevenliebregt.feesboek.application.javalin.web.endpoint.TokenEndpoint
import com.stevenliebregt.feesboek.application.javalin.web.endpoint.UserEndpoint
import com.stevenliebregt.feesboek.common.jwt.JwtProvider
import com.stevenliebregt.feesboek.data.exposed.ExposedPostRepository
import com.stevenliebregt.feesboek.data.exposed.ExposedSetup
import com.stevenliebregt.feesboek.data.exposed.ExposedUserRepository
import com.stevenliebregt.feesboek.usecase.CreateUserUseCase
import com.stevenliebregt.feesboek.usecase.FindPostUseCase
import com.stevenliebregt.feesboek.usecase.LoginUseCase
import com.stevenliebregt.feesboek.usecase.LogoutUseCase
import com.stevenliebregt.feesboek.usecase.repository.IPostRepository
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository
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
        single { TokenController(get(), get()) }
    }

    private val userModule = module {
        single { UserEndpoint(get()) } bind Endpoint::class
        single { UserController(get()) }
    }

    private val postModule = module {
        single { PostEndpoint(get()) } bind Endpoint::class
        single { PostController(get()) }
    }

    private val repositoryModule = module {
        single { ExposedSetup(get()) } // Makes sure the tables are created

        single<IUserRepository> { ExposedUserRepository(get()) }
        single<IPostRepository> { ExposedPostRepository(get()) }
    }

    private val useCaseModule = module {
        single { CreateUserUseCase(get()) }
        single { LoginUseCase(get(), get()) }
        single { LogoutUseCase(get()) }
        single { FindPostUseCase(get()) }
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