package example.service;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  RentalService
@version  1.0.0
@since 19.11.2025 - 23.48
*/

import example.dto.RentalRequest;
import example.dto.StatementResponse;
import example.model.Movie;
import example.model.Rental;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    public StatementResponse generateStatement(String customerName, List<RentalRequest> requests) {
        double total = 0;
        int points = 0;

        for (RentalRequest req : requests) {
            Movie movie = new Movie(req.title(), req.type());
            Rental rental = new Rental(movie, req.days());

            total += movie.getCharge(req.days());
            points += movie.getFrequentRenterPoints(req.days());
        }

        return new StatementResponse(customerName, total, points);
    }
}
