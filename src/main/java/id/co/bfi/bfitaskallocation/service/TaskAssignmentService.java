package id.co.bfi.bfitaskallocation.service;

import id.co.bfi.bfitaskallocation.dto.CreateTaskAssignmentRequest;
import id.co.bfi.bfitaskallocation.dto.TaskAssignmentResponse;

public interface TaskAssignmentService {
  public TaskAssignmentResponse getTaskAssignment(Long id);

  public TaskAssignmentResponse createTaskAssignment(CreateTaskAssignmentRequest createTaskAssignment);
}
