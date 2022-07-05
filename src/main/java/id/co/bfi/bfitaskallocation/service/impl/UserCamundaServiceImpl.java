package id.co.bfi.bfitaskallocation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import id.co.bfi.bfitaskallocation.service.UserCamundaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCamundaServiceImpl implements UserCamundaService {
  private String assignTo;

  @Override
  public Integer countUserByGroupName(DelegateExecution execution, String groupName) {
    return execution.getProcessEngineServices()
        .getIdentityService()
        .createGroupQuery()
        .groupId(groupName)
        .list()
        .size();
  }

  @Override
  public String getUserByRoundRobin(DelegateExecution execution, String groupName) {
    List<String> listUserName = new ArrayList<>();

    var usersInGroup = execution.getProcessEngineServices().getIdentityService().createUserQuery()
        .memberOfGroup(groupName).list();

    if (usersInGroup.size() > 0) {
      for (int i = 0; i < usersInGroup.size(); i++) {
        String userName = usersInGroup.get(i).getId();
        listUserName.add(userName);
      }

      if (listUserName.size() % 2 != 0) {
        listUserName.add(listUserName.get(1)); // If odd number of user add user index
      }

      int numInterval = (listUserName.size() - 1); // Interval round
      // int halfSize = listUserName.size() / 2;

      List<String> participants = new ArrayList<>();
      participants.addAll(listUserName); // Add user to List and remove the first user
      participants.remove(0);

      int listUserSize = participants.size();

      for (int itv = 0; itv < numInterval; itv++) {
        log.info("INTERVAL : " + (itv + 1));

        int userIdx = itv % listUserSize;

        log.info("Compare: " + participants.get(userIdx) + " and " + listUserName.get(0));

        var countTaskFirst = execution.getProcessEngineServices().getTaskService().createTaskQuery()
            .taskAssignee(listUserName.get(0))
            .list().size();

        log.info(listUserName.get(0) + " HAVE " + countTaskFirst + " TASK ");

        var countTaskSecond = execution.getProcessEngineServices().getTaskService().createTaskQuery()
            .taskAssignee(participants.get(userIdx))
            .list().size();

        log.info(participants.get(userIdx) + " HAVE " + countTaskSecond + " TASK ");

        if (countTaskFirst < countTaskSecond) {
          log.info("Assign: " + listUserName.get(0));
          assignTo = listUserName.get(0);
        } else {
          log.info("Assign: " + participants.get(userIdx));
          assignTo = participants.get(userIdx);
        }

      }

    }
    return assignTo;
  }
}
