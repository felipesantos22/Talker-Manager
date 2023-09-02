package com.talker.manager;

import com.talker.manager.model.Talker;
import com.talker.manager.repository.TalkerRepository;
import com.talker.manager.service.TalkerService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestTalkerService {

  @Autowired
  TalkerService talkerService;
  @MockBean
  TalkerRepository talkerRepository;

  @Test
  public void testCreateTalker() {
    Talker talker = new Talker();
    talker.setName("Felipe");
    talker.setAge(33L);

    Talker talkerToReturn = new Talker();
    talkerToReturn.setId(UUID.randomUUID());
    talkerToReturn.setName(talker.getName());
    talkerToReturn.setAge(talker.getAge());

    Mockito.when(talkerRepository.save(ArgumentMatchers.any(Talker.class)))
        .thenReturn(talkerToReturn);

    Talker saveTalker = talkerService.createService(talker);

    Mockito.verify(talkerRepository).save(ArgumentMatchers.any(Talker.class));

    Assertions.assertEquals(talkerToReturn.getId(), saveTalker.getId());
    Assertions.assertEquals(talker.getName(), saveTalker.getName());
    Assertions.assertEquals(talker.getAge(), saveTalker.getAge());
  }
}
