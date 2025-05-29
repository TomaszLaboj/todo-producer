package com.tomasz_laboj.simplified_todo.controller;

import com.tomasz_laboj.simplified_todo.repository.TaskLabel;

public class ToDoItemRequest {
    private String title;
    private TaskLabel label;

    public String getTitle() {
        return title;
    }

    public TaskLabel getLabel() {
        return label;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLabel(TaskLabel label) {
        this.label = label;
    }
}
