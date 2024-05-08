package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.openschool.aop.backend.model.AnimalType;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {

}
