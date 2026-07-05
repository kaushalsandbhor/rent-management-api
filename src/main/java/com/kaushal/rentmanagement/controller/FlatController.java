package com.kaushal.rentmanagement.controller;

import com.kaushal.rentmanagement.dto.FlatDropdownDto;
import com.kaushal.rentmanagement.service.FlatService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flats")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://moonlit-crisp-51b17b.netlify.app"
})
public class FlatController {

    private final FlatService flatService;

    public FlatController(FlatService flatService) {
        this.flatService = flatService;
    }

    @GetMapping("/vacant")
    public List<FlatDropdownDto> getVacantFlats(
            @RequestParam LocalDate joiningDate
    ) {
        return flatService.getVacantFlats(joiningDate);
    }

}