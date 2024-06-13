package com.example.web_dev_learn

import org.springframework.data.jpa.repository.JpaRepository

interface ToDoRepository : JpaRepository<TodoItem, Long>