package com.talker.manager;

import com.talker.manager.model.Talker;
import com.talker.manager.repository.TalkerRepository;
import com.talker.manager.service.TalkerService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestGetServiceTalker {
  @MockBean
  TalkerRepository talkerRepository;
  @Autowired
  TalkerService talkerService;
  @Test
  public void testFindAll(){

    Talker talker1 = new Talker(UUID.randomUUID(),"Felipe",32L);
    Talker talker2 = new Talker(UUID.randomUUID(),"Felipe",32L);

    List<Talker> talkers = Arrays.asList(talker1,talker2);

    Mockito.when(talkerRepository.findAll()).thenReturn(talkers);

    List<Talker> talkerList = talkerService.findAllService();

    Assertions.assertEquals(talkers, talkerList);

  }

}
