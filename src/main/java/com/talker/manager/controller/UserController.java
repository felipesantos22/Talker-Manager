package com.talker.manager.controller;

import com.talker.manager.model.User;
import com.talker.manager.service.UserService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return service.findByIdUser(id)
        .map(record -> ResponseEntity.status(HttpStatus.OK).body(record))
        .orElse(ResponseEntity.notFound().build());

  }

  @GetMapping(path = {"/email/{email}"})
  public Optional<User> findByName(@PathVariable String email) {
    return service.findEmail(email);
  }

  @GetMapping(path = {"/date/{localDate}"})
  public List<User> local(@PathVariable LocalDate localDate) {
    return service.localDateUser(localDate);
  }

  @DeleteMapping(path = {"/{id}"})
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    Optional<User> optionalUser = service.deleteUser(id);
    if (optionalUser.isPresent()) {
      return ResponseEntity.ok().body("Usúario deletado!");
    } else {
      return ResponseEntity.ok().body("Usúario deletado!");
    }
  }


}
