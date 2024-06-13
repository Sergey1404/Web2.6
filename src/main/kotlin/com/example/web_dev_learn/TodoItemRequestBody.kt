package com.example.web_dev_learn


data class TodoItemRequestBody(
    val title: String?,
    val desc: String = "",
    val subtodosJson: List<TodoItemRequestBody>? = emptyList()
)
