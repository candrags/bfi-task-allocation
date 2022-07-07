package id.co.bfi.bfitaskallocation.service;

import java.util.List;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public interface GroupCamundaService {
  public Integer countGroupByUserName(DelegateExecution execution, String userName);

  public List<String> getListMemberGroupByGroupName(DelegateExecution execution, String groupName);
}
