package id.co.bfi.bfitaskallocation.service;

import id.co.bfi.bfitaskallocation.dto.CreateTaskAssignmentRequest;
import id.co.bfi.bfitaskallocation.dto.TaskAssignmentResponse;
import id.co.bfi.bfitaskallocation.dto.UpdateTaskAssignmentRequest;

public interface TaskAssignmentService {
  public TaskAssignmentResponse getTaskAssignment(Long id);

  public TaskAssignmentResponse createTaskAssignment(CreateTaskAssignmentRequest createTaskAssignment);

  public TaskAssignmentResponse updateStatusTaskAssignment(Long id, UpdateTaskAssignmentRequest updateTaskAssignmentRequest);
}
