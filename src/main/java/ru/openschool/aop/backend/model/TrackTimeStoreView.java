package ru.openschool.aop.backend.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Tikhonov Arkadiy
 * Статические параметры выполнения методов
 */
@Entity
@Getter
public class TrackTimeStoreView {

    /**
     * Наименование метода
     */
    @Id
    String methodName;

    /**
     * Количество выполнений
     */
    Long execCount;

    /**
     * Суммарное время выполнений
     */
    Long execSum;

    /**
     * Минимальное время  выполнения
     */
    Long execMin;

    /**
     * Максимальное время  выполнения
     */
    Long execMax;
    /**
     * Среднее время  выполнения
     */
    Long execAvg;
}
