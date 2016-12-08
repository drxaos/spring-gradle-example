package com.example.controller.api;


import com.example.db.Message;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/api/last")
    public ApiLast last() {
        List<ApiMessage> data = new ArrayList<>();
        for (Message message : messageService.lastMessages()) {
            data.add(new ApiMessage(message));
        }
        return new ApiLast(data);
    }

}
