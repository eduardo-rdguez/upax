package rdguez.eduardo.upax.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rdguez.eduardo.upax.domain.Job;
import rdguez.eduardo.upax.repository.JobRepository;
import rdguez.eduardo.upax.service.JobService;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

  @Autowired
  JobRepository jobRepository;

  @Override
  @Transactional(readOnly = true)
  public Optional<Job> findJobById(Long id) {
    return jobRepository.findById(id);
  }

}
