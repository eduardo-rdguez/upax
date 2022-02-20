package rdguez.eduardo.upax.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import rdguez.eduardo.upax.domain.Job;
import rdguez.eduardo.upax.repository.JobRepository;

import java.util.Optional;

@SpringBootTest
class JobServiceImplTest {

  @InjectMocks
  JobServiceImpl jobService;

  @Mock
  JobRepository jobRepository;

  @Test
  void findJobById() {
    Mockito
      .when(jobRepository.findById(Mockito.anyLong()))
      .thenReturn(Optional.of(new Job()));

    Optional<Job> job = jobService.findJobById(1L);

    assert job.isPresent();
  }

}