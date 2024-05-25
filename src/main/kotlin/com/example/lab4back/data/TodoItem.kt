package com.example.lab4back.data

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ToDo")
data class TodoItem(
        @Id
        val id: ObjectId = ObjectId(),
        val text: String = "",
        @ElementCollection
        val subtasksIds: List<ObjectId> = emptyList(),
        val state: Boolean = false
)
