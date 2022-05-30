package com.example.docker3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    private final UserRepository userRepository;
    private final String username;

    @Autowired
    public Init(UserRepository userRepository,
                @Value("${init.username}") String username) {
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("INIT.---------------------------------------------------------");

        if (userRepository.count() == 0) {
            userRepository.save(new User(username));
            userRepository.flush();
        }
    }
}
