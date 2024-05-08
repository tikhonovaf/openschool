package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.openschool.aop.backend.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
