package id.co.bfi.bfitaskallocation.service.impl;

import id.co.bfi.bfitaskallocation.dto.CreateTaskAssignmentRequest;
import id.co.bfi.bfitaskallocation.dto.TaskAssignmentResponse;
import id.co.bfi.bfitaskallocation.entity.TaskAssignment;
import id.co.bfi.bfitaskallocation.repository.TaskAssignmentRepository;
import id.co.bfi.bfitaskallocation.service.TaskAssignmentService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

  @Autowired
  private TaskAssignmentRepository taskAssignmentRepository;

  @Override
  public TaskAssignmentResponse getTaskAssignment(Long id) {
    TaskAssignment taskAssignment = taskAssignmentRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    log.debug("Requested ID task assignment record found!");

    return TaskAssignmentResponse.builder()
        .id(taskAssignment.getId())
        .transactionCode(taskAssignment.getTransactionCode())
        .moduleName(taskAssignment.getModuleName())
        .taskName(taskAssignment.getTaskName())
        .taskDescription(taskAssignment.getTaskDescription())
        .assignmentType(taskAssignment.getAssignmentType())
        .assignTo(taskAssignment.getAssignTo())
        .linkUrl(taskAssignment.getLinkUrl())
        .taskStatus(taskAssignment.getTaskStatus())
        .createdAt(taskAssignment.getCreatedAt())
        .updatedAt(taskAssignment.getUpdatedAt())
        .build();
  }

  @Override
  public TaskAssignmentResponse createTaskAssignment(CreateTaskAssignmentRequest createTaskAssignment) {
    TaskAssignment taskAssignment = taskAssignmentRepository.save(TaskAssignment.builder()
        .transactionCode(createTaskAssignment.getTransactionCode())
        .moduleName(createTaskAssignment.getModuleName())
        .taskName(createTaskAssignment.getTaskName())
        .taskDescription(createTaskAssignment.getTaskDescription())
        .assignmentType(createTaskAssignment.getAssignmentType())
        .assignTo(createTaskAssignment.getAssignTo())
        .taskStatus(createTaskAssignment.getTaskStatus())
        .linkUrl(createTaskAssignment.getLinkUrl())
        .createdAt(LocalDateTime.now())
        .build());

    log.info("New task assignment record created !");

    return TaskAssignmentResponse.builder()
        .id(taskAssignment.getId())
        .transactionCode(taskAssignment.getTransactionCode())
        .moduleName(taskAssignment.getModuleName())
        .taskName(taskAssignment.getTaskName())
        .taskDescription(taskAssignment.getTaskDescription())
        .assignmentType(taskAssignment.getAssignmentType())
        .assignTo(taskAssignment.getAssignTo())
        .taskStatus(taskAssignment.getTaskStatus())
        .createdAt(taskAssignment.getCreatedAt())
        .linkUrl(taskAssignment.getLinkUrl())
        .updatedAt(taskAssignment.getUpdatedAt())
        .build();
  }
}
