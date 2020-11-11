package com.stevenliebregt.feesboek.db.h2hikaricp

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import javax.sql.DataSource

class Database(jdbcUrl: String, username: String, password: String) {
    val dataSource: DataSource by lazy {
        HikariConfig().let { config ->
            config.jdbcUrl = jdbcUrl
            config.username = username
            config.password = password

            HikariDataSource(config)
        }
    }

    init {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8100").start()
    }
}