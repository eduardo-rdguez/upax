package rdguez.eduardo.upax.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EmployeeWorkedHoursRepository extends CrudRepository<EmployeeWorkedHours, Long> {

  Optional<EmployeeWorkedHours> findOneByEmployee_IdAndWorkedDate(Long id, Date workedDate);

}
