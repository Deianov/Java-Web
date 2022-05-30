package com.example.docker3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String username;
    private final String message;
    private final UserRepository userRepository;

    public HelloController(@Value("${init.username}") String username,
                           @Value("${init.message}") String message,
                           UserRepository userRepository) {
        this.username = username;
        this.message = message;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String hello() {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            String msg = String.format("%s, %s", user.getUsername(), this.message);
            return msg;
        } else {
            return "Not found user!";
        }
    }
}
