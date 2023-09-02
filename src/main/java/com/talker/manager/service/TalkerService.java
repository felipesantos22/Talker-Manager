package com.talker.manager.service;

import com.talker.manager.model.Talker;
import com.talker.manager.repository.TalkerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TalkerService {

  private final TalkerRepository talkerRepository;

  public TalkerService(TalkerRepository talkerRepository) {
    this.talkerRepository = talkerRepository;
  }

  public Talker createService(Talker talker) {
    return talkerRepository.save(talker);
  }

  public List<Talker> findAllService(){
    return talkerRepository.findAll();
  }

  public Optional<Talker> findById(UUID uuid){
    return talkerRepository.findById(uuid);
  }



}
