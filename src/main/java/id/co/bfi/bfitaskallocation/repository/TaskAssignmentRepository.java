package id.co.bfi.bfitaskallocation.repository;

import id.co.bfi.bfitaskallocation.entity.TaskAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAssignmentRepository extends CrudRepository<TaskAssignment, Long> {}
