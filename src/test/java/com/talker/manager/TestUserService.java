package com.talker.manager;

import com.talker.manager.model.User;
import com.talker.manager.repository.UserRepository;
import com.talker.manager.service.UserService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestUserService {

  @MockBean
  UserRepository userRepository;
  @Autowired
  UserService userService;

  @Test
  public void testUserCreation() {
    User user = new User();
    user.setEmail("felipe@gmail.com");
    user.setPassword("123456");

    User userReturn = new User();
    userReturn.setId(UUID.randomUUID());
    userReturn.setEmail(user.getEmail());
    userReturn.setPassword(user.getPassword());

    Mockito.when(userRepository.save(ArgumentMatchers.any(User.class)))
        .thenReturn(userReturn);

    User saveUser = userService.createUser(user);

    Assertions.assertEquals(userReturn.getId(), saveUser.getId());
    Assertions.assertEquals(userReturn.getEmail(), saveUser.getEmail());
    Assertions.assertEquals(userReturn.getPassword(), saveUser.getPassword());

  }

}
