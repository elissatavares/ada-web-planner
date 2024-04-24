package com.ada.web.planner.infra.repository;

import com.ada.web.planner.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlannerRepository extends JpaRepository<User, UUID> {
}
