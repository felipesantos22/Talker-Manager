package com.talker.manager.repository;

import com.talker.manager.model.Talker;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkerRepository extends JpaRepository<Talker, UUID> {

}
