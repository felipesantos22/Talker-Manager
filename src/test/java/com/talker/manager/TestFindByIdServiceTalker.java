package com.talker.manager;

import com.talker.manager.model.Talker;
import com.talker.manager.repository.TalkerRepository;
import com.talker.manager.service.TalkerService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestFindByIdServiceTalker {

  @Autowired
  TalkerService talkerService;

  @MockBean
  TalkerRepository talkerRepository;
  @Test
  public void testFindById(){
    Talker talker = new Talker();
    talker.setId(UUID.randomUUID());
    talker.setName("Felipe");
    talker.setAge(33L);

    Mockito.when(talkerRepository.findById(ArgumentMatchers.eq(talker.getId())))
        .thenReturn(Optional.of(talker));

    Optional<Talker> returnedTalker = talkerService.findById(talker.getId());

    Mockito.verify(talkerRepository).findById(ArgumentMatchers.eq(talker.getId()));

    Assertions.assertEquals(returnedTalker.get().getId(), talker.getId());
    Assertions.assertEquals(returnedTalker.get().getName(), talker.getName());
    Assertions.assertEquals(returnedTalker.get().getAge(), talker.getAge());

  }

}
