package com.stevenliebregt.feesboek.application.javalin.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

class DbConfig(jdbcUrl: String, username: String, password: String) {
    private val dataSource: DataSource

    init {
        // TODO: NOT HERE
        dataSource = HikariConfig().let { config ->
            config.jdbcUrl = jdbcUrl
            config.username = username
            config.password = password

            HikariDataSource(config)
        }
    }

    fun getDataSource(): DataSource = dataSource
}