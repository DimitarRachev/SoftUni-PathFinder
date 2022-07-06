package com.example.pathfinder.util;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrentUser {

    private Long id;

    private String username;
    }
