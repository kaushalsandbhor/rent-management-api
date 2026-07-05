package com.kaushal.rentmanagement.controller;

import com.kaushal.rentmanagement.dto.CollectPaymentDto;
import com.kaushal.rentmanagement.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://moonlit-crisp-51b17b.netlify.app"
})
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/collect")
    public String collectPayment(
            @RequestBody CollectPaymentDto dto
    ) {

        paymentService.collectPayment(dto);

        return "Payment saved successfully.";

    }

}