package com.warehouse.model.entity;

import com.warehouse.model.enums.LogType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log extends BaseLongEntity {

    @Column
    private byte level;

    @Column
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private LogType type;

    @Column
    private String description;


    public Log() {
        this.date = LocalDateTime.now();
    }
    public Log(byte level, LogType type, String description) {
        this();
        this.level = level;
        this.type = type;
        this.description = description;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
