package com.soit23.enterprise.dao;

import com.soit23.enterprise.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
