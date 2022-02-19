package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.domain.Job;

import java.util.Optional;

public interface JobService {

  Optional<Job> findJobById(Long id);

}
