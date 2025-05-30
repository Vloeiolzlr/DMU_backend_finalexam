package com.dmubackend.spring.bread.repository;

import com.dmubackend.spring.bread.entity.Bread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadRepository extends JpaRepository<Bread, Long> {
}
