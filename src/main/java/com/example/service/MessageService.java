package com.example.service;

import com.example.db.Message;
import com.example.db.MessageRepository;
import com.example.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> lastMessages() {
        Page<Message> messages = messageRepository.findAll(new PageRequest(0, 20, Sort.Direction.DESC, "date"));
        List<Message> list = new ArrayList<Message>();
        messages.forEach(list::add);
        return list;
    }

    public void addMessage(User user, String message) {
        Message m = new Message();
        m.setDate(new Date());
        m.setUser(user);
        m.setMessage(message);
        messageRepository.save(m);
    }

    @Scheduled(fixedRate = 1000000)
    public void clean() {
        System.out.println("CLEAN");
    }

}
