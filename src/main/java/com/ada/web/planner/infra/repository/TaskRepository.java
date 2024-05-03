package com.ada.web.planner.infra.repository;

import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    List<Task> findAllByUserId(User userId);
}
