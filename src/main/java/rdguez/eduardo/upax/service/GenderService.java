package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.domain.Gender;

import java.util.Optional;

public interface GenderService {

  Optional<Gender> findGenderById(Long id);

}
