package rdguez.eduardo.upax.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rdguez.eduardo.upax.domain.Gender;
import rdguez.eduardo.upax.repository.GenderRepository;
import rdguez.eduardo.upax.service.GenderService;

import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {

  @Autowired
  GenderRepository genderRepository;

  @Override
  @Transactional(readOnly = true)
  public Optional<Gender> findGenderById(Long id) {
    return genderRepository.findById(id);
  }

}
