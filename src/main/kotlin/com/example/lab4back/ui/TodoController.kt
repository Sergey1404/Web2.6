package com.example.lab4back.ui

import com.example.lab4back.data.TodoItem
import com.example.lab4back.data.TodoRepository
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class TodoController(private val todoRepository: TodoRepository) {
    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("todos", todoRepository.findAll())
        return "index"
    }

    @GetMapping("/switchState/{todoId}")
    fun switchState(model: Model, @PathVariable todoId: Long): String {
        val todoItem = todoRepository.findById(todoId).orElseThrow { NoSuchElementException("Todo not found") }
        val newTodoItem = todoItem.copy(state = !todoItem.state)
        todoRepository.save(newTodoItem)
        return "redirect:/"
    }

    @DeleteMapping("/delete/{todoId}")
    fun delete(model: Model, @PathVariable todoId: Long): String {
        todoRepository.deleteById(todoId)
        return "redirect:/"
    }

    @PostMapping("/add")
    fun add(model: Model, @ModelAttribute todoItem: TodoItem): String {
        if (todoRepository.findAll().map { it.id }.containsAll(todoItem.subtasksIds.map { it })) {
            todoRepository.save(todoItem)
        }
        return "redirect:/"
    }
}