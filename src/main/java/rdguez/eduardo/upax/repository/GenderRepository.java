package rdguez.eduardo.upax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rdguez.eduardo.upax.domain.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

}
