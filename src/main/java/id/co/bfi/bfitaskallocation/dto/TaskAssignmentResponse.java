package id.co.bfi.bfitaskallocation.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignmentResponse {

  private Long id;
  private String transactionCode;
  private String moduleName;
  private String taskName;
  private String taskDescription;
  private String linkUrl;
  private String assignmentType;
  private String assignTo;
  private String taskStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
