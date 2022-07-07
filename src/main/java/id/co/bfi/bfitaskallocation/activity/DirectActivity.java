package id.co.bfi.bfitaskallocation.activity;

import id.co.bfi.bfitaskallocation.constant.ActivityConstants;
import id.co.bfi.bfitaskallocation.service.GroupCamundaService;
import id.co.bfi.bfitaskallocation.service.UserCamundaService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DirectActivity implements JavaDelegate {

  @Autowired
  private UserCamundaService userService;

  @Autowired
  private GroupCamundaService groupService;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String assignTo = (String) execution.getVariable(ActivityConstants.DIRECT_ASSIGN_TO);
    log.debug("assignTo is: " + assignTo);
    var countUser = userService.countUserByGroupName(execution, assignTo);
    var countGroup = groupService.countGroupByUserName(execution, assignTo);
    execution.setVariable(ActivityConstants.DIRECT_COUNT_USER, countUser);
    execution.setVariable(ActivityConstants.DIRECT_COUNT_GROUP, countGroup);
  }
}
