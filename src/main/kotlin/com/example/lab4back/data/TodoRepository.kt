package com.example.lab4back.data

import org.springframework.data.mongodb.repository.MongoRepository

interface TodoRepository : MongoRepository<TodoItem, Long>