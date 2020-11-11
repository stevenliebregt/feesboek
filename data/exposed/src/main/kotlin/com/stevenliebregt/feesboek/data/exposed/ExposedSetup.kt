package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.data.exposed.table.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

class ExposedSetup(private val dataSource: DataSource) {
    fun setup() {
        transaction(Database.connect(dataSource)) {
            SchemaUtils.create(
                    UsersTable
            )
        }
    }
}