package com.example.mockitojunit45.service.impl;

import com.example.mockitojunit45.domain.Student;
import com.example.mockitojunit45.dto.CreateStudentDto;
import com.example.mockitojunit45.dto.StudentDto;
import com.example.mockitojunit45.repository.StudentRespository;
import com.example.mockitojunit45.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRespository studentRespository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto getStudent(Long id) {
        Student student = studentRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (student.getAge() > 18) {
            return modelMapper.map(student, StudentDto.class);
        } else {
            throw new RuntimeException("You don't pass age requirements");
        }
    }

    @Override
    public StudentDto create(CreateStudentDto studentDto) {
        studentDto.setName(studentDto.getName().toUpperCase());
        Student student = modelMapper.map(studentDto, Student.class);
        student.setCreationDate(new Date());
        Student saved = studentRespository.save(student);
        return modelMapper.map(saved, StudentDto.class);
    }

    @Override
    public StudentDto update(Long id, CreateStudentDto studentDto) {
        studentRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Student student = modelMapper.map(studentDto, Student.class);
        student.setId(id);
        studentRespository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void delete(Long id) {
        studentRespository.deleteById(id);
    }
}
