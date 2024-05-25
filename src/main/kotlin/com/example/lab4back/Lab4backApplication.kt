package com.example.lab4back

import com.example.lab4back.data.TodoItem
import com.example.lab4back.data.TodoRepository
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class Lab4backApplication {
	@Autowired lateinit var todoRepository: TodoRepository
	val logger: Logger = LoggerFactory.getLogger(Lab4backApplication::class.java)

	@PostConstruct
	fun initRepository() {
		repeat(10) {
			todoRepository.save(TodoItem(text = "Test task $it", subtasksIds = emptyList()))
		}
		val todos = todoRepository.findAll()
		todoRepository.save(TodoItem(text = "New task", subtasksIds = listOf(todos.toList().random().id)))
		logger.info(todoRepository.findAll().toString())
	}
}

fun main(args: Array<String>) {
	runApplication<Lab4backApplication>(*args)
}
