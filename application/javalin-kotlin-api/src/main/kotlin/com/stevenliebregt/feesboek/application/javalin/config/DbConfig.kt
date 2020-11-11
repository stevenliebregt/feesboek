package com.stevenliebregt.feesboek.application.javalin.config

import javax.sql.DataSource
import com.stevenliebregt.feesboek.db.h2hikaricp.Database

class DbConfig(jdbcUrl: String, username: String, password: String) {
    val dataSource: DataSource by lazy { Database(jdbcUrl, username, password).dataSource }
}