package com.example.mockitojunit45.controller;

import com.example.mockitojunit45.dto.StudentDto;
import com.example.mockitojunit45.dto.WelcomeMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public WelcomeMessageDto sayHello(@RequestHeader("Accept-Language") String lang, @RequestBody StudentDto studentDto) {
        log.info("Receive request: {}", studentDto);
        return lang.equalsIgnoreCase("AZ") ?
                new WelcomeMessageDto("Salam " + studentDto.getName())
                : new WelcomeMessageDto("Hello " + studentDto.getName());
    }
}
