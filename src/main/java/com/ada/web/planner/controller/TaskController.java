package com.ada.web.planner.controller;

import com.ada.web.planner.dto.response.TaskResponseFactory;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.usecases.task.CreateTask;
import com.ada.web.planner.core.usecases.task.DeleteTask;
import com.ada.web.planner.core.usecases.task.ReadTask;
import com.ada.web.planner.core.usecases.task.UpdateTask;
import com.ada.web.planner.dto.response.ResponseDTO;
import com.ada.web.planner.dto.task.CreateTaskRequestDTO;
import com.ada.web.planner.dto.task.TaskDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planner/task")
public class TaskController {

    private final CreateTask createService;

    private final ReadTask readService;

    private final UpdateTask updateService;

    private final DeleteTask deleteService;

    public TaskController(CreateTask createService, ReadTask readService, UpdateTask updateService, DeleteTask deleteService) {
        this.createService = createService;
        this.readService = readService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @Operation(description = "Exibe os detalhes de todas as tarefas de um usuário")
    @GetMapping
    public ResponseEntity<ResponseDTO> readAllTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TaskDTO> taskDTOList = readService.readALl(UUID.fromString(authentication.getName()));
        ResponseDTO requestResultDTO = TaskResponseFactory.taskDetails(taskDTOList);
        return ResponseEntity.ok().body(requestResultDTO);
    }

    @Operation(description = "Exibe os detalhes de uma tarefa específica de um usuário")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> readTask(@PathVariable @NotNull Long id) {
        TaskDTO taskDTO = readService.read(id);
        ResponseDTO requestResultDTO = TaskResponseFactory.taskDetails(taskDTO);
        return ResponseEntity.ok().body(requestResultDTO);
    }

    @Operation(description = "Cria uma tarefa")
    @PostMapping
    public ResponseEntity<ResponseDTO> createTask(@RequestBody CreateTaskRequestDTO taskData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Task task = new Task(taskData);
        System.out.println(taskData);
        System.out.println(task.getTitle());
        TaskDTO taskDTO = createService.create(task, UUID.fromString(authentication.getName()));
        ResponseDTO requestResultDTO = TaskResponseFactory.createdSuccessfully(taskDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.CREATED);
    }

    @Operation(description = "Marca uma tarefa como concluída")
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> markTaskAsCompleted(@PathVariable @NotNull Long id) {
        TaskDTO taskDTO = updateService.completed(id);
        ResponseDTO requestResultDTO = TaskResponseFactory.updatedSuccessfully(taskDTO);
        return ResponseEntity.ok().body(requestResultDTO);
    }

    @Operation(description = "Deleta uma tarefa")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable @NotNull Long id) {
        TaskDTO taskDTO = deleteService.delete(id);
        ResponseDTO requestResultDTO = TaskResponseFactory.deletedSuccessfully(taskDTO);
        return ResponseEntity.ok().body(requestResultDTO);
    }
}
