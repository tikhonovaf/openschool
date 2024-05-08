package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.openschool.aop.backend.model.Action;


public interface ActionRepository extends JpaRepository<Action, Long> {
}
