package ru.openschool.aop.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Tikhonov Arkadiy
 */
@Entity
@Getter
public class AnimalView {

    /**
     * Идентификатор
     */
    @Id
    Long id;

    /**
     * Наименование
     */
    String name;

    /**
     * Идентифткатор типа
     */
    Long animalTypeId;

    /**
     * НГаименование типа
     */
    String animalTypeName;

    /**
     * Количество ног
     */
    Long legs;

}
