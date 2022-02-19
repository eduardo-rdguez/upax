package rdguez.eduardo.upax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rdguez.eduardo.upax.domain.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findOneByNameAndLastName(String name, String lastName);

  List<Employee> findAllByJob_Id(Long id);

}
