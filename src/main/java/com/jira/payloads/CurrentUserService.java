package com.jira.payloads;

import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.jira.controller.CommentController;
import com.jira.entities.Users;
import com.jira.exception.ResourceNotFoundException;
import com.jira.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CurrentUserService {


    private final UserRepository userRepository;

    

    public Users getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(()
        		->new ResourceNotFoundException("User not found")
        		);
    }
}