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
import com.stevenliebregt.feesboek.usecase.*
import com.stevenliebregt.feesboek.usecase.repository.IPostRepository
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

object ModulesConfig {
    private val configModule = module {
        single<AppConfig>()
        single<JwtProvider>()
        single<AuthConfig>()
        single<EndpointConfig>()

        single {
            DbConfig(
                    getProperty("db.jdbc_url"),
                    getProperty("db.username"),
                    getProperty("db.password")
            ).dataSource
        }
    }

    private val tokenModule = module {
        single<TokenEndpoint>() bind Endpoint::class
        single<TokenController>()
    }

    private val userModule = module {
        single<UserEndpoint>() bind Endpoint::class
        single<UserController>()
    }

    private val postModule = module {
        single<PostEndpoint>() bind Endpoint::class
        single<PostController>()
    }

    private val repositoryModule = module {
        single<ExposedSetup>()

        singleBy<IUserRepository, ExposedUserRepository>()
        singleBy<IPostRepository, ExposedPostRepository>()
    }

    private val useCaseModule = module {
        single<CreateUserUseCase>()
        single<FindUserUseCase>()

        single<LoginUseCase>()
        single<LogoutUseCase>()

        single<CreatePostUseCase>()
        single<FindPostUseCase>()
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