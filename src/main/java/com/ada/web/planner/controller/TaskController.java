package com.ada.web.planner.controller;

import com.ada.web.planner.controller.response.TaskResponseFactory;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.usecases.task.CreateTask;
import com.ada.web.planner.core.usecases.task.DeleteTask;
import com.ada.web.planner.core.usecases.task.ReadTask;
import com.ada.web.planner.core.usecases.task.UpdateTask;
import com.ada.web.planner.dto.response.ResponseDTO;
import com.ada.web.planner.dto.task.CreateTaskRequestDTO;
import com.ada.web.planner.dto.task.TaskDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planner/tasks/{login}")
public class TaskController {

    private final CreateTask createService;

    private final ReadTask readService;

    private final UpdateTask updateService;

    private final DeleteTask deleteService;

    @Autowired
    public TaskController(CreateTask createService, ReadTask readService, UpdateTask updateService, DeleteTask deleteService) {
        this.createService = createService;
        this.readService = readService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> readAllTasks(@PathVariable @NotBlank String login) {
        List<Task> taskList = readService.readALl(login);
        List<TaskDTO> taskDTOList = taskList.stream().map(TaskDTO::toDTO).toList();
        ResponseDTO requestResultDTO = TaskResponseFactory.taskDetails(taskDTOList);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTask(@RequestBody @Valid CreateTaskRequestDTO taskData,
                                                  @PathVariable @NotBlank String login) {
        Task task = new Task(taskData);
        Task taskCreated = createService.create(task, login);
        TaskDTO taskDTO = TaskDTO.toDTO(taskCreated);
        ResponseDTO requestResultDTO = TaskResponseFactory.createdSuccessfully(taskDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> readTask(@PathVariable @NotBlank String login, @PathVariable @NotNull Long id) {
        Task taskCreated = readService.read(id, login);
        TaskDTO taskDTO = TaskDTO.toDTO(taskCreated);
        ResponseDTO requestResultDTO = TaskResponseFactory.taskDetails(taskDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.OK);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CreateTaskResponseDTO> updateTaks(@PathVariable String id) {
//        // Implementation to update a specific todo by ID
//        return new ResponseEntity<>(new CreateTaskResponseDTO(), HttpStatus.OK);
//    }
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> markTaskAsCompleted(@PathVariable @NotBlank String login, @PathVariable @NotNull Long id) {
        Task task = updateService.completed(id, login);
        TaskDTO taskDTO = TaskDTO.toDTO(task);
        ResponseDTO requestResultDTO = TaskResponseFactory.updatedSuccessfully(taskDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable @NotBlank String login, @PathVariable @NotNull Long id) {
        Task task = deleteService.delete(id, login);
        TaskDTO taskDTO = TaskDTO.toDTO(task);
        ResponseDTO requestResultDTO = TaskResponseFactory.deletedSuccessfully(taskDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.OK);
    }
}
