package com.stevenliebregt.feesboek.db.h2hikaricp

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
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
}