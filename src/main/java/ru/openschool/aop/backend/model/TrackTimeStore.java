package ru.openschool.aop.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Tikhonov Arkadiy
 */
@Entity
@Getter
@Setter
public class TrackTimeStore {

    /**
     * Идентификатор записи
     */
    @Id
    @GeneratedValue
    Long id;

    /**
     * Наименование метода
     */
    String methodName;

    /**
     * Время выполнения в мсек
     */
    Long executionTime;

}
