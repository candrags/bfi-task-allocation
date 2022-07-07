package id.co.bfi.bfitaskallocation.activity;

import id.co.bfi.bfitaskallocation.constant.ActivityConstants;
import id.co.bfi.bfitaskallocation.service.UserCamundaService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoundRobinActivity implements JavaDelegate {

  @Autowired
  private UserCamundaService userService;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String groupName = (String) execution.getVariable(ActivityConstants.ROUNDROBIN_ASSIGN_TO_GROUP);
    log.debug("The group name is: " + groupName);
    String assignTo = userService.getUserByRoundRobin(execution, groupName);
    execution.setVariable(ActivityConstants.ROUNDROBIN_ASSIGN_TO_USER, assignTo);
  }
}
