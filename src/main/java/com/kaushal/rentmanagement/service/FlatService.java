package com.kaushal.rentmanagement.service;

import com.kaushal.rentmanagement.dto.FlatDropdownDto;
import com.kaushal.rentmanagement.entity.Flat;
import com.kaushal.rentmanagement.repository.FlatRepository;
import com.kaushal.rentmanagement.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlatService {

    private final FlatRepository flatRepository;
    private final TenantRepository tenantRepository;

    public FlatService(
            FlatRepository flatRepository,
            TenantRepository tenantRepository
    ) {
        this.flatRepository = flatRepository;
        this.tenantRepository = tenantRepository;
    }

    public List<FlatDropdownDto> getVacantFlats(LocalDate joiningDate) {

        List<Flat> allFlats = flatRepository.findAll();

        List<Long> occupiedFlatIds =
                tenantRepository.findOccupiedFlatIds(joiningDate);

        return allFlats.stream()
                .filter(flat -> !occupiedFlatIds.contains(flat.getId()))
                .map(flat -> new FlatDropdownDto(
                        flat.getId(),
                        flat.getFlatNumber()
                ))
                .toList();
    }

    

}