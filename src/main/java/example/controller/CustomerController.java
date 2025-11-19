package example.controller;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  CustomerController
@version  1.0.0
@since 19.11.2025 - 23.47
*/

import example.dto.RentalRequest;
import example.dto.StatementResponse;
import example.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class CustomerController {

    private final RentalService rentalService;

    public CustomerController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/statement")
    public StatementResponse getStatement(
            @RequestParam String customer,
            @RequestBody List<RentalRequest> rentals
    ) {
        return rentalService.generateStatement(customer, rentals);
    }
}

