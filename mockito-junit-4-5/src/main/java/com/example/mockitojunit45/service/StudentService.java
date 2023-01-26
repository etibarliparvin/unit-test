package com.example.mockitojunit45.service;

import com.example.mockitojunit45.dto.CreateStudentDto;
import com.example.mockitojunit45.dto.StudentDto;

public interface StudentService {

    StudentDto getStudent(Long id);

    StudentDto create(CreateStudentDto studentDto);

    StudentDto update(Long id, CreateStudentDto studentDto);

    void delete(Long id);
}
