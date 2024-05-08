package ru.openschool.aop.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Tikhonov Arkadiy
 */
@Entity
@Getter
@Setter
public class Access {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    Long id;

    /**
     * Роль
     */
    @ManyToOne
    Role role;

    /**
     * Ресурс
     */
    @ManyToOne
    Resource resource;

    /**
     * Действие
     */
    @ManyToOne
    Action action;

}
