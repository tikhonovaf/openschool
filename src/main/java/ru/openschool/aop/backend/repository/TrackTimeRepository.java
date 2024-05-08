package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.openschool.aop.backend.model.TrackTimeStore;

public interface TrackTimeRepository extends JpaRepository<TrackTimeStore, Long> {

}
