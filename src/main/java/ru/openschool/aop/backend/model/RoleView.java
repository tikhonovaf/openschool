package ru.openschool.aop.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.validation.constraints.NotNull;

/**
 * Created by Tikhonov Arkadiy
 */
@Entity
@Getter
@Setter
public class RoleView {

    /**
     * Идентификатор
     */
    @Id
    Long id;

    /**
     * Наименование
     */
    String name;


}
