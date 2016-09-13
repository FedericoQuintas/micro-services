package de.bonify.session.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/api/users")
    public String authorized() {
        return "Hello Secured World";
    }
}
