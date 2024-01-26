package com.example.api.controller.admin;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {
    @GetMapping
    public String get(){
        return "Admin get";
    }

    @PostMapping()
    public String post(){
        return "Admin get";
    }

    @PutMapping
    public String put(){
        return "Admin put";
    }

    @DeleteMapping
    public String delete(){
        return "Admin delete";
    }
}
