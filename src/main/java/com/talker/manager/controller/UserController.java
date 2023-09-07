package com.talker.manager.controller;

import com.talker.manager.model.User;
import com.talker.manager.service.UserService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping
  public ResponseEntity<List<User>> read() {
    List<User> userList = service.readUser();
    return ResponseEntity.ok().body(userList);
  }

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<?> findById(@PathVariable UUID id) {
    return service.findByIdUser(id)
        .map(record -> ResponseEntity.status(HttpStatus.OK).body(record))
        .orElse(ResponseEntity.notFound().build());

  }
}
