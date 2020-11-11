package com.stevenliebregt.feesboek.data.exposed

import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

abstract class Repository(private val dataSource: DataSource) {
    protected val connection: Database
        get() = Database.connect(dataSource)
}