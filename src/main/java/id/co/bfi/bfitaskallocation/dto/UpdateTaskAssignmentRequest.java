package id.co.bfi.bfitaskallocation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskAssignmentRequest {

  @NotBlank
  @Schema(description = "Type of Task Assignment", example = "direct")
  private String assignmentType;

  @NotBlank
  @Schema(description = "NIK PIC Task", example = "089326")
  private String assignTo;

  @NotBlank
  @Schema(description = "Status Task", example = "DONE")
  private String taskStatus;
}
