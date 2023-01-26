package com.example.mockitojunit45.controller;

import com.example.mockitojunit45.dto.CreateStudentDto;
import com.example.mockitojunit45.dto.StudentDto;
import com.example.mockitojunit45.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody CreateStudentDto studentDto) {
        return studentService.create(studentDto);
    }

    @PutMapping("/{id}")
    public StudentDto updateStudent(@PathVariable Long id, @RequestBody CreateStudentDto studentDto) {
        return studentService.update(id, studentDto);
    }

    @DeleteMapping("/{id}")
    public void updateStudent(@PathVariable Long id) {
        studentService.delete(id);
    }
}
