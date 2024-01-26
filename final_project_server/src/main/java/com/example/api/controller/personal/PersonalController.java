package com.example.api.controller.personal;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/personal")
public class PersonalController {

    @GetMapping
    public String get(){
        return "personal get";
    }
    @PostMapping()
    public String post(){
        return "personal post";
    }

    @PutMapping
    public String put(){
        return "personal put";
    }

    @DeleteMapping
    public String delete(){
        return "personal delete";
    }
}