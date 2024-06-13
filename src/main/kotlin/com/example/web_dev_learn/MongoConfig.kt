package com.example.web_dev_learn

import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class MongoConfig {
    @Value("\${spring.data.mongodb.uri}")
    private lateinit var mongoUri: String

    @Value("\${spring.data.mongodb.database}")
    private lateinit var database: String

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val mongoDbFactory = SimpleMongoClientDatabaseFactory(MongoClients.create(mongoUri), database)
        return MongoTemplate(mongoDbFactory)
    }
}