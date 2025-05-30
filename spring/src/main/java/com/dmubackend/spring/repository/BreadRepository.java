package com.dmubackend.spring.repository;

import com.dmubackend.spring.entity.Bread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadRepository extends JpaRepository<Bread, Long> {
}
