package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.data.exposed.table.Posts
import com.stevenliebregt.feesboek.data.exposed.table.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import javax.sql.DataSource

class ExposedSetup(private val dataSource: DataSource) {
    fun setup() {
        transaction(Database.connect(dataSource)) {
            SchemaUtils.create(
                Users,
                Posts
            )

            val userId = Users.insertAndGetId { row ->
                row[email] = "admin@example.com"
                row[password] = ""
            }.value

            Posts.insert { row ->
                row[title] = "Post 1"
                row[body] = "Lorem ipsum dolor sit amet"
                row[createdAt] = DateTime.now()
                row[authorId] = userId
            }

            Posts.insert { row ->
                row[title] = "Post 2"
                row[body] = "Lorem ipsum dolor sit amet"
                row[createdAt] = DateTime.now()
                row[authorId] = userId
            }
        }
    }
}