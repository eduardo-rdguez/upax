package rdguez.eduardo.upax.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import rdguez.eduardo.upax.domain.Gender;
import rdguez.eduardo.upax.repository.GenderRepository;

import java.util.Optional;

@SpringBootTest
class GenderServiceImplTest {

  @InjectMocks
  GenderServiceImpl genderService;

  @Mock
  GenderRepository genderRepository;

  @Test
  void findGenderById() {
    Mockito
      .when(genderRepository.findById(Mockito.anyLong()))
      .thenReturn(Optional.of(new Gender()));

    Optional<Gender> gender = genderService.findGenderById(1L);

    assert gender.isPresent();
  }

}