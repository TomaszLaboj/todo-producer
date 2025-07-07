package com.tomasz_laboj.simplified_todo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasz_laboj.simplified_todo.repository.ToDoItem;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private class TodoItemUpdated {

        public ToDoItem original;
        public ToDoItem updated;

        public TodoItemUpdated(ToDoItem original, ToDoItem updated) {
            this.original = original;
            this.updated = updated;
        }
    }


    ObjectMapper mapper = new ObjectMapper();

    public void sendCreated(ToDoItem toDoItem) throws JsonProcessingException {
        kafkaTemplate.send("created", mapper.writeValueAsString(toDoItem));
    };

    public void sendUpdated(ToDoItem originalToDoItem, ToDoItem updatedToDoItem) throws JsonProcessingException {
        if (!originalToDoItem.getLabel().equals(updatedToDoItem.getLabel()) || (originalToDoItem.isDone() != updatedToDoItem.isDone())) {
            TodoItemUpdated todoItemUpdated = new TodoItemUpdated(originalToDoItem, updatedToDoItem);
            kafkaTemplate.send("updated", mapper.writeValueAsString(todoItemUpdated));
        }
    }

    public void sendDeleted(ToDoItem toDoItem) throws JsonProcessingException {
        kafkaTemplate.send("deleted", mapper.writeValueAsString(toDoItem));
    }
}
