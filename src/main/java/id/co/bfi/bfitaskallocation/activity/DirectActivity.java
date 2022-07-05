package id.co.bfi.bfitaskallocation.activity;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import id.co.bfi.bfitaskallocation.service.GroupCamundaService;
import id.co.bfi.bfitaskallocation.service.UserCamundaService;

@Component
public class DirectActivity implements JavaDelegate {

  @Autowired
  private UserCamundaService _userService;

  @Autowired
  private GroupCamundaService _groupService;
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String assignTo = (String) execution.getVariable("assignTo");
    System.out.println(" assignTo is: " + assignTo);

    var countUser = _userService.countUserByGroupName(execution, assignTo);

    var countGroup = _groupService.countGroupByUserName(execution, assignTo);

    execution.setVariable("countUser", countUser);
    execution.setVariable("countGroup", countGroup);
    
  }
  
}