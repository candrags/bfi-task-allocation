package id.co.bfi.bfitaskallocation.service.impl;

import id.co.bfi.bfitaskallocation.service.GroupCamundaService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GroupCamundaServiceImpl implements GroupCamundaService {

  @Override
  public Integer countGroupByUserName(DelegateExecution execution, String userName) {
    return execution.getProcessEngineServices().getIdentityService().createUserQuery().userId(userName).list().size();
  }

  @Override
  public List<String> getListMemberGroupByGroupName(DelegateExecution execution, String groupName) {
    List<String> listUserName = new ArrayList<>();
    log.debug("The group name is: " + groupName);
    var users = execution
      .getProcessEngineServices()
      .getIdentityService()
      .createUserQuery()
      .memberOfGroup(groupName)
      .list();

    for (int i = 0; i < users.size(); i++) {
      String userName = users.get(i).getId();
      log.debug("The username name is: " + userName);
      listUserName.add(userName);
    }
    return listUserName;
  }
}
