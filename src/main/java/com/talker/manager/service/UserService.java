package com.talker.manager.service;

import com.talker.manager.model.User;
import com.talker.manager.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User createUser(User user) {
    String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
    user.setPassword(hashedPassword);
    return repository.save(user);
  }

  public List<User> readUser() {
    return repository.findAll();
  }

  public Optional<User> findByIdUser(UUID id) {
    return repository.findById(id);
  }

}
