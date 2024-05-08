package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.openschool.aop.backend.model.TrackTimeStoreView;

public interface TrackTimeViewRepository extends JpaRepository<TrackTimeStoreView, Long> {

}
