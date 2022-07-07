package id.co.bfi.bfitaskallocation.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public interface UserCamundaService {
  public Integer countUserByGroupName(DelegateExecution execution, String groupName);

  public String getUserByRoundRobin(DelegateExecution execution, String groupName);
}
