package com.warehouse.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseLongEntity{
}
