package com.stevenliebregt.feesboek.application.javalin

import com.stevenliebregt.feesboek.application.javalin.config.AppConfig
import com.stevenliebregt.feesboek.application.javalin.config.ModulesConfig
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        fileProperties()
        environmentProperties()

        printLogger()

        modules(ModulesConfig.allModules)

        AppConfig()
                .setup()
                .start()
    }
}