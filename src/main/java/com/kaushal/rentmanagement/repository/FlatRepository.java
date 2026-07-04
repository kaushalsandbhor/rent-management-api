package com.kaushal.rentmanagement.repository;

import com.kaushal.rentmanagement.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepository extends JpaRepository<Flat, Long> {
}