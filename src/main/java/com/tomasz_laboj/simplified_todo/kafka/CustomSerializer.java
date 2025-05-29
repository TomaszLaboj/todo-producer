package com.tomasz_laboj.simplified_todo.kafka;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasz_laboj.simplified_todo.repository.ToDoItem;

public class CustomSerializer implements Serializer<ToDoItem> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, ToDoItem todoItem) {
        try {
            if (todoItem == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(todoItem);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing object to byte[]");
        }
    }

    @Override
    public void close() {
    }
}
