package com.apiProject.Api.controller;

import com.apiProject.Api.entity.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private List<Message> messages = new ArrayList<>();

    // constructor
    public MessageController() {
        messages.add(new Message(1, "Hola"));
        messages.add(new Message(2, "Mundo"));
    }

    // Getmapping
    @GetMapping
    public List<Message> getMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable int id){
        Optional<Message> message = messages.stream()
                .filter(i -> i.getId() == id)
                .findFirst();
        return message.orElse(null);
    }
    @PostMapping("/create")
    public Message createMessage(@RequestBody Message message){
         messages.add(message);
         return message;
    }
    @DeleteMapping("/delete/{id}")
    public String deleteMessage(@PathVariable int id){
        messages.removeIf(m -> m.getId() == id );
        return "El mensaje con id: " + id + " se ha eliminado";
    }
}
