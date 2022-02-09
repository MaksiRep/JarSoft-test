package ru.maksirep.jarsoft.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maksirep.jarsoft.controller.requests.LoginRequest;


@RestController
@CrossOrigin(origins = {"http://localhost:3000/"})
public class LoginController {

    @PostMapping(value = "/login")
    public void login(LoginRequest request) {
    }
}
