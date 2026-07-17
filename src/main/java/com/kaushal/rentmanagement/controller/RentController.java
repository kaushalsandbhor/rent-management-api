package com.kaushal.rentmanagement.controller;

import com.kaushal.rentmanagement.dto.ChangeRentDto;
import com.kaushal.rentmanagement.dto.CurrentRentDto;
import com.kaushal.rentmanagement.dto.RentHistoryDto;
import com.kaushal.rentmanagement.service.RentService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/rent")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://rent-management-ui.onrender.com"
})
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/current")
    public CurrentRentDto current() {

        return rentService.getCurrentRent();

    }

    @PostMapping("/change")
    public String change(
            @Valid @RequestBody ChangeRentDto dto
    ) {

        rentService.changeRent(dto);

        return "Rent updated successfully.";
    }

    @GetMapping("/history")
    public List<RentHistoryDto> history() {

        return rentService.getRentHistory();

    }


}