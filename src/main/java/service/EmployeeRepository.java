package service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByName(String name);

}
