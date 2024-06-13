package com.example.web_dev_learn

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ToDoController(
    private val todoRepo: ToDoRepository
) {
    init {
        todoRepo.save(
            TodoItem(
                title = "Learn Spring Boot",
                desc = "Learn how to use Spring Boot",
                status = Status.TODO
            )
        )
    }

    @GetMapping("/todo")
    fun getTodos(): List<TodoItem> {
        return todoRepo.findAll()
    }

    @GetMapping("/todo/{id}")
    fun getTodo(@PathVariable id: Long): TodoItem {
        return todoRepo.findById(id).get()
    }

    @PostMapping("/todo")
    fun createTodo(
        @RequestBody httpEntity: TodoItemRequestBody
    ): TodoItem {
        return todoRepo.save(
            TodoItem(
                title = httpEntity.title.orEmpty(),
                desc = httpEntity.desc.orEmpty(),
                status = Status.TODO
            )
        )
    }

    @PutMapping("/todo/{id}")
    fun editTodo(
        @RequestBody httpEntity: TodoItemRequestBody,
        @PathVariable id: Long
    ): TodoItem {
        val subtodos = httpEntity.subtodosJson?.map {
            TodoItem(
                title = it.title.orEmpty(),
                desc = it.desc.orEmpty(),
                status = Status.TODO
            )
        }

        val todo = todoRepo.findById(id).get()
        val newTodo = todo.copy(
            title = httpEntity.title.orEmpty(),
            desc = httpEntity.desc,
            status = Status.TODO
        )
        todoRepo.deleteById(id)

        return todoRepo.save(newTodo)
    }

    @DeleteMapping("todo/{id}")
    fun deleteTodo(
        @PathVariable id: Long
    ) {
        todoRepo.deleteById(id)
    }
}