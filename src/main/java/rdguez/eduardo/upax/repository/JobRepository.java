package rdguez.eduardo.upax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rdguez.eduardo.upax.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
