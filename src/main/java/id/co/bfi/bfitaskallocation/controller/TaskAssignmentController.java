package id.co.bfi.bfitaskallocation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.bfi.bfitaskallocation.dto.CreateTaskAssignmentRequest;
import id.co.bfi.bfitaskallocation.dto.TaskAssignmentResponse;
import id.co.bfi.bfitaskallocation.dto.UpdateTaskAssignmentRequest;
import id.co.bfi.bfitaskallocation.service.TaskAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "TaskAssignment", description = "Controller for CRUD Task Assignment")
public class TaskAssignmentController {
  @Autowired
  private TaskAssignmentService taskAssignmentService;

  @PutMapping(value = "/task_assignment/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Update Task Assignment", operationId = "updateTaskAssignment")
  public TaskAssignmentResponse updateTaskAssignment(@PathVariable @Schema(description = "Task Assignment Id", example = "1") Long id, @Valid @RequestBody UpdateTaskAssignmentRequest updateTaskAssignmentRequest) {
    log.debug("Invoking PUT on /task_assignment/"+id+" route");
    return taskAssignmentService.updateStatusTaskAssignment(id, updateTaskAssignmentRequest);
  }

  @GetMapping(value = "/task_assignment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Get Task Assignment by Id", operationId = "getTaskById")
  public TaskAssignmentResponse getTaskById(@Valid @PathVariable @Schema(description = "Task Assignment Id", example = "1") Long id) {
    log.debug("Invoking GET on /task_assignment/"+id+" route");
    return taskAssignmentService.getTaskAssignment(id);
  }

  @PostMapping(value = "/task_assignment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new Task Assignment", operationId = "createTaskAssignment")
  public TaskAssignmentResponse createTaskAssignment(@Valid @RequestBody CreateTaskAssignmentRequest createTaskAssignmentRequest) {
    log.debug("Invoking POST on /task_assignment route");
    return taskAssignmentService.createTaskAssignment(createTaskAssignmentRequest);
  }


}
