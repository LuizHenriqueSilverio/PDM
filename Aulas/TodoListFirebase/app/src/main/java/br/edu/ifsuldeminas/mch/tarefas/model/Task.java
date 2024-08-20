package br.edu.ifsuldeminas.mch.tarefas.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Task implements Serializable {

    private String id;
    private String description;
    private Boolean active;
    private Date dateChanged;

    public Task() {
        this.active = true;
    }

    public Task(String id, String desc) {
        this.id = id;
        setDescription(desc);
        this.setActive(true);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged() {
        dateChanged = Calendar.getInstance().getTime();
    }
}
