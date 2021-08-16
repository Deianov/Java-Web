package com.warehouse.model.entity;

import com.warehouse.model.Dimension;
import com.warehouse.model.enums.Units;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends Dimension {

    private static final class Options {
        // TODO: 19.07.2020 class Package
        private static final int DEFAULT_PACKING_UNIT = 1;
        // TODO: 19.07.2020 hard-coding
        private static final Units DEFAULT_QUANTITY_UNITS = Units.ITEM;
        // TODO: 19.07.2020 validate: European Article Number (EAN-13)
        private static final Long EXEMPLARY_ARTICLE_NUMBER = 3801234567898L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Access(AccessType.PROPERTY)
    private long id;

    @Column
    private String name;

    @Column
    private Long ean = Options.EXEMPLARY_ARTICLE_NUMBER;

    @Transient
    private Units units = Options.DEFAULT_QUANTITY_UNITS;

    @Column(name = "packing_unit")
    private int packingUnit = Options.DEFAULT_PACKING_UNIT;

    @Column
    private boolean enabled;

}
