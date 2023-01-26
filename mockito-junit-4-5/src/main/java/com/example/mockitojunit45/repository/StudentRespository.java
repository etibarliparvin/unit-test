package com.example.mockitojunit45.repository;

import com.example.mockitojunit45.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<Student, Long> {
}
