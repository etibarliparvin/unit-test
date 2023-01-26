package com.example.mockitojunit45.service.impl;

import com.example.mockitojunit45.domain.Student;
import com.example.mockitojunit45.dto.CreateStudentDto;
import com.example.mockitojunit45.dto.StudentDto;
import com.example.mockitojunit45.repository.StudentRespository;
import com.example.mockitojunit45.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    private StudentService studentService;
    @Mock
    private StudentRespository studentRespository;
    @Mock
    private ArgumentCaptor<Student> studentArgumentCaptor;

    @BeforeEach
    void beforeEach() {
        ModelMapper modelMapper = new ModelMapper();
        studentService = new StudentServiceImpl(studentRespository, modelMapper);
    }

    @Test
    public void whenNoStudentThenThrowStudentNotFound() {
        assertThatThrownBy(() -> studentService.getStudent(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Student not found");
    }

    @Test
    public void whenGetStudentThenReturnStudentDto() {
        // Arrange
        Student student = Student.builder()
                .id(2L)
                .name("Hafiz")
                .age(24L)
                .build();
        when(studentRespository.findById(2L)).thenReturn(Optional.of(student));
        // Act
        StudentDto studentDto = studentService.getStudent(2L);

        // Assert
        assertThat(studentDto.getName()).isEqualTo("Hafiz");
        assertThat(studentDto.getAge()).isEqualTo(24L);
        assertThat(studentDto.getId()).isEqualTo(2L);
    }

    @Test
    public void whenGetStudentWithAgeLessThan18ThenThrowException() {
        // Arrange
        Student student = Student.builder()
                .id(2L)
                .name("Hafiz")
                .age(2L)
                .build();
        when(studentRespository.findById(2L)).thenReturn(Optional.of(student));
        // Act & Assert
        assertThatThrownBy(() -> studentService.getStudent(2L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("You don't pass age requirements");
    }

    @Test
    public void whenCreateStudentThenNameUpperCase() {
        // Arrange
        CreateStudentDto createStudentDto = new CreateStudentDto();
        createStudentDto.setName("hafiz");
        createStudentDto.setAge(30L);

        Student student = new Student();
        student.setName("HAFIZ");
        student.setAge(30L);
        when(studentRespository.save(student)).thenReturn(student);

        // Act
        studentService.create(createStudentDto);

        // Assert
        verify(studentRespository, times(1)).save(student);
    }

    @Test
    public void whenCreateStudentThenNameUpperCaseCaptor() {
        // Arrange
        CreateStudentDto createStudentDto = new CreateStudentDto();
        createStudentDto.setName("hafiz");
        createStudentDto.setAge(30L);

        Student student = new Student();
        student.setName("HAFIZ");
        student.setAge(30L);
        student.setCreationDate(any());
        when(studentRespository.save(student)).thenReturn(student);

        // Act
        studentService.create(createStudentDto);

        // Assert
        verify(studentRespository, times(1))
                .save(studentArgumentCaptor.capture());
        Student value = studentArgumentCaptor.getValue();
        assertThat(value.getCreationDate()).isNotNull();
    }

//    @Test
//    public void whenCreateStudentThenNameUpperCase() {
//        // Arrange
//        CreateStudentDto createStudentDto = new CreateStudentDto();
//        createStudentDto.setName("hafiz");
//        createStudentDto.setAge(30L);
//
//        Student student = new Student();
//        student.setName("hafiz");
//        student.setAge(30L);
//
//        StudentDto studentDto1 = new StudentDto();
//        studentDto1.setName("hafiz");
//        studentDto1.setAge(30L);
//
//        StudentDto studentDto2 = new StudentDto();
//        studentDto2.setName("hafiz");
//        studentDto2.setAge(30L);
//        studentDto2.setId(1L);
//
//        when(modelMapper.map(createStudentDto, Student.class)).thenReturn(student);
//        when(modelMapper.map(student, StudentDto.class)).thenReturn(studentDto1);
//        when(studentService.create(createStudentDto)).thenReturn(studentDto2);
//
//        // Act
//        studentService.create(createStudentDto);
//
//        // Assert
//        verify(studentRespository, times(1)).save(student);
//    }
}