package com.tomasz_laboj.simplified_todo.repository;


import java.io.Serializable;

import org.apache.kafka.common.protocol.Message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDoItem implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String title;
    private TaskLabel label;

    private boolean isDone;

    protected ToDoItem() {}

    public ToDoItem(String title, TaskLabel label, boolean isDone) {
        this.title = title;
        this.label = label;
        this.isDone = isDone;
    }

    public ToDoItem(Long id, String title, TaskLabel label, boolean isDone) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; };

    public TaskLabel getLabel() { return label; }
    public void setLabel(TaskLabel label) { this.label = label; }

    @JsonProperty("isDone")
    public boolean isDone() {return isDone;}
    public void setIsDone(boolean isDone) { this.isDone = isDone; }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}