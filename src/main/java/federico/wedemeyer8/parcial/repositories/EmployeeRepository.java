package federico.wedemeyer8.parcial.repositories;

import federico.wedemeyer8.parcial.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
