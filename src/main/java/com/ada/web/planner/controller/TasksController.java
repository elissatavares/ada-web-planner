package com.ada.web.planner.controller;

import com.ada.web.planner.dto.task.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planner/tasks")
public class TasksController {


    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        // Implementation to get all user's todos
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<TaskResponseDTO> addTask() {
//        // Implementation to add a new todo
//        return new ResponseEntity<>(new TaskResponseDTO(), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
//        // Implementation to get details of a specific todo by ID
//        return new ResponseEntity<>(new TaskResponseDTO(), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TaskResponseDTO> updateTaks(@PathVariable String id) {
//        // Implementation to update a specific todo by ID
//        return new ResponseEntity<>(new TaskResponseDTO(), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/user/todos/{id}")
//    public ResponseEntity<TaskResponseDTO> deleteTodo(@PathVariable String id) {
//        // Implementation to delete a specific todo by ID
//        return new ResponseEntity<>(new TaskResponseDTO(), HttpStatus.OK);
//    }
}
