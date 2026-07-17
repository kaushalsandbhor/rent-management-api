package com.kaushal.rentmanagement.repository;

import com.kaushal.rentmanagement.entity.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentHistoryRepository
        extends JpaRepository<RentHistory, Long> {

    boolean existsByEffectiveFrom(LocalDate effectiveFrom);

    Optional<RentHistory>
    findTopByEffectiveFromLessThanEqualOrderByEffectiveFromDesc(
            java.time.LocalDate date
    );

    Optional<RentHistory>
    findTopByOrderByEffectiveFromDesc();

    List<RentHistory> findAllByOrderByEffectiveFromDesc();

}