package com.kaushal.rentmanagement.service;

import com.kaushal.rentmanagement.dto.ChangeRentDto;
import com.kaushal.rentmanagement.dto.CurrentRentDto;
import com.kaushal.rentmanagement.dto.RentHistoryDto;
import com.kaushal.rentmanagement.entity.RentHistory;
import com.kaushal.rentmanagement.repository.RentHistoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {

    private final RentHistoryRepository rentHistoryRepository;

    public RentService(RentHistoryRepository rentHistoryRepository) {
        this.rentHistoryRepository = rentHistoryRepository;
    }

    public Double getRentForDate(LocalDate billingDate) {

        return rentHistoryRepository
                .findTopByEffectiveFromLessThanEqualOrderByEffectiveFromDesc(
                        billingDate
                )
                .map(RentHistory::getRentAmount)
                .orElse(0.0);

    }

    public CurrentRentDto getCurrentRent() {

        RentHistory rent =
                rentHistoryRepository
                        .findTopByOrderByEffectiveFromDesc()
                        .orElseThrow();

        return new CurrentRentDto(

                rent.getRentAmount(),

                rent.getEffectiveFrom()

        );

    }

    public void changeRent(ChangeRentDto dto) {

        if (dto.getEffectiveFrom().getDayOfMonth() != 1) {

            throw new RuntimeException(
                    "Rent can only be changed from the first day of a month."
            );

        }

        LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);

        if (dto.getEffectiveFrom().isBefore(currentMonth)) {

            throw new RuntimeException(
                    "Rent cannot be changed for a past month."
            );

        }

        if (rentHistoryRepository.existsByEffectiveFrom(
                dto.getEffectiveFrom())) {

            throw new RuntimeException(
                    "Rent already exists for this effective date."
            );

        }

        RentHistory rent = new RentHistory();

        rent.setRentAmount(dto.getRentAmount());
        rent.setEffectiveFrom(dto.getEffectiveFrom());

        rentHistoryRepository.save(rent);

    }

    public List<RentHistoryDto> getRentHistory() {

        return rentHistoryRepository
                .findAllByOrderByEffectiveFromDesc()
                .stream()
                .map(r -> new RentHistoryDto(
                        r.getId(),
                        r.getRentAmount(),
                        r.getEffectiveFrom()
                ))
                .toList();

    }

}