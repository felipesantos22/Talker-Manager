package com.talker.manager;

import com.talker.manager.model.Talker;
import com.talker.manager.repository.TalkerRepository;
import com.talker.manager.service.TalkerService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestPutTalkerService {

  @MockBean
  TalkerRepository talkerRepository;

  @Autowired
  TalkerService talkerService;
  @Test
  public void testPut(){

    Talker talker = new Talker(UUID.randomUUID(), "Felipe", 32L);

    Mockito.when(talkerRepository.findById(talker.getId())).thenReturn(Optional.of(talker));
    Mockito.when(talkerRepository.save(talker)).thenReturn(talker);

    Optional<Talker> updateTalker = talkerService.updateService(talker.getId(), talker);

  }

}
