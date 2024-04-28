package com.ada.web.planner.controller;

import com.ada.web.planner.dto.task.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planner/tasks")
public class TaskController {


    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        // Implementation to get all user's todos
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<CreateTaskResponseDTO> addTask() {
//        // Implementation to add a new todo
//        return new ResponseEntity<>(new CreateTaskResponseDTO(), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CreateTaskResponseDTO> getTaskById(@PathVariable Long id) {
//        // Implementation to get details of a specific todo by ID
//        return new ResponseEntity<>(new CreateTaskResponseDTO(), HttpStatus.OK);
//    }

//
//    @PutMapping("/{id}")
//    public ResponseEntity<CreateTaskResponseDTO> updateTaks(@PathVariable String id) {
//        // Implementation to update a specific todo by ID
//        return new ResponseEntity<>(new CreateTaskResponseDTO(), HttpStatus.OK);
//    }
//  @PatchMapping("/{id}")
//      public ResponseEntity<?> markTaskAsCompleted(@PathVariable String id) {
    //    // Lógica para marcar a tarefa como concluída
    //    try {
    //        Todo updatedTodo = todoService.markTaskAsCompleted(id);
    //        return ResponseEntity.ok(updatedTodo);
    //    } catch (TodoNotFoundException e) {
    //        return ResponseEntity.notFound().build();
//    }
//}
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<CreateTaskResponseDTO> deleteTodo(@PathVariable String id) {
//        // Implementation to delete a specific todo by ID
//        return new ResponseEntity<>(new CreateTaskResponseDTO(), HttpStatus.OK);
//    }
}
