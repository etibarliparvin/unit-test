package com.example.mockitojunit45.repository;

import com.example.mockitojunit45.domain.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step, Long> {
}