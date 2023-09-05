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

  public Optional<Talker> updateService(UUID uuid, Talker talker){
    return talkerRepository.findById(uuid)
        .map(record -> {
          record.setName(talker.getName());
          record.setAge(talker.getAge());
          return talkerRepository.save(record);
        });
  }

  public Optional<Talker> deleteService(UUID uuid){
    return talkerRepository.findById(uuid).map(record -> {
      talkerRepository.deleteById(uuid);
      return record;
    });
  }




}
