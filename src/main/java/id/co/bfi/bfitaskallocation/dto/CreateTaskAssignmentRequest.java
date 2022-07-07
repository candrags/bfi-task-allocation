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
public class CreateTaskAssignmentRequest {

  @NotBlank
  @Schema(description = "Transaction Code for Task Assignment", example = "TRANS #123")
  private String transactionCode;

  @NotBlank
  @Schema(description = "Module Application Name", example = "Incentive")
  private String moduleName;

  @NotBlank
  @Schema(description = "Task Todo Name", example = "Check Data")
  private String taskName;

  @NotBlank
  @Schema(description = "Task Todo Description", example = "Please check Report Finalisasi Payroll")
  private String taskDescription;

  @NotBlank
  @Schema(description = "Url Module Application", example = "https://app.bfi.co.id/new-incentive")
  private String linkUrl;

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
