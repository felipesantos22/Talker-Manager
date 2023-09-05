package com.talker.manager.controller;

import com.talker.manager.model.Talker;
import com.talker.manager.service.TalkerService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("talker")
public class TalkerController {

  private final TalkerService talkerService;

  public TalkerController(TalkerService talkerService) {
    this.talkerService = talkerService;
  }

  @PostMapping
  public ResponseEntity<Talker> create(@RequestBody Talker talker) {
    Talker tk = talkerService.createService(talker);
    return ResponseEntity.status(HttpStatus.CREATED).body(tk);
  }

  @GetMapping
  public ResponseEntity<List<Talker>> findAll() {
    List<Talker> talkers = talkerService.findAllService();
    return ResponseEntity.status(HttpStatus.OK).body(talkers);
  }

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<?> findById(@PathVariable UUID id) {
    Optional<Talker> talker = talkerService.findById(id);
    if (talker.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(talker);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado!");
    }
  }

  @PutMapping(path = {"/{id}"})
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Talker talker) {
    Optional<Talker> tkId = talkerService.findById(id);
    if (tkId.isPresent()) {
      Optional<Talker> tk = talkerService.updateService(id, talker);
      return ResponseEntity.status(HttpStatus.OK).body(tk);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }
  }

  @DeleteMapping(path = {"/{id}"})
  public ResponseEntity<?> deleteTalker(@PathVariable UUID id) {
    Optional<Talker> tkId = talkerService.findById(id);
    if (tkId.isPresent()) {
      Optional<Talker> talker = talkerService.deleteService(id);
      return ResponseEntity.status(HttpStatus.OK).body(talker);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }
  }

}
