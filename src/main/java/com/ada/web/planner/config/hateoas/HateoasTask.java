package com.ada.web.planner.config.hateoas;

import com.ada.web.planner.controller.TaskController;
import com.ada.web.planner.core.model.Task;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HateoasTask {

    public Task hrefTasks(Task task){
        return task.add(relAllTasks(), relSelfReadfLink(task), relSelfDeleteLink(task), relSelfCompleteLink(task));
    }

    public List<Task> relSelfTask(List<Task> taskList){
        return taskList.stream()
                .map(task -> task.add(relSelfReadfLink(task), relSelfDeleteLink(task), relSelfCompleteLink(task))).toList();
    }

    public Link relSelfReadfLink(Task task) {
        return linkTo(methodOn(TaskController.class).readTask(task.getId()))
                .withRel("self")
                .withType(HttpMethod.GET.name());
    }

    public Link relSelfDeleteLink(Task task) {
        return linkTo(methodOn(TaskController.class).deleteTask(task.getId()))
                .withRel("self")
                .withType(HttpMethod.DELETE.name());
    }

    public Link relSelfCompleteLink(Task task) {
        return linkTo(methodOn(TaskController.class).markTaskAsCompleted(task.getId()))
                .withRel("self")
                .withType(HttpMethod.PATCH.name());
    }

    public Link relAllTasks() {
        return linkTo(methodOn(TaskController.class).readAllTasks())
                .withRel("all tasks")
                .withType(HttpMethod.GET.name());
    }
}
