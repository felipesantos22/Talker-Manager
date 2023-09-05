package com.talker.manager.controller;

import com.talker.manager.model.User;
import com.talker.manager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody User user) {
    User users = service.createUser(user);
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(users);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Erro ao criar usúario!");
    }
  }
}
