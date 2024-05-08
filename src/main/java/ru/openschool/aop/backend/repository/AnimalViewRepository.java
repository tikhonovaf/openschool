package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.openschool.aop.backend.model.AnimalView;

public interface AnimalViewRepository extends JpaRepository<AnimalView, Long> {

}
