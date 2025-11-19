package example;

import example.dto.RentalRequest;
import example.dto.StatementResponse;
import example.model.MovieType;
import example.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerTest {

    @Autowired
    private RentalService rentalService;

    private String remboTitle;
    private MovieType remboType;

    private String lotrTitle;
    private MovieType lotrType;

    private String hpTitle;
    private MovieType hpType;

    @BeforeEach
    void setUp() {
        remboTitle = "Rembo";
        remboType = MovieType.REGULAR;

        lotrTitle = "Lord of the Rings";
        lotrType = MovieType.NEW_RELEASE;

        hpTitle = "Harry Potter";
        hpType = MovieType.CHILDRENS;
    }

    @Test
    @DisplayName("Statement with mixed rentals")
    void whenStatementRequestedWithMixedRentals_thenReturnsFormattedStatement() {
        List<RentalRequest> requests = List.of(
                new RentalRequest(remboTitle, remboType, 1),
                new RentalRequest(lotrTitle, lotrType, 4),
                new RentalRequest(hpTitle, hpType, 5)
        );

        StatementResponse res = rentalService.generateStatement("Alex AT", requests);

        assertEquals("Alex AT", res.customerName());
        assertEquals(18.5, res.totalAmount(), 1e-9);
        assertEquals(4, res.points());
    }

    @Test
    @DisplayName("Long regular rental")
    void whenRegularMovieRentedManyDays_thenChargeIsCalculatedCorrectly() {
        StatementResponse res = rentalService.generateStatement("LongRent",
                List.of(new RentalRequest(remboTitle, remboType, 10)));

        assertEquals("LongRent", res.customerName());
        assertEquals(14.0, res.totalAmount(), 1e-9);
        assertEquals(1, res.points());
    }

    @Test
    @DisplayName("Empty rentals")
    void whenNoRentals_thenStatementShowsZeroAmounts() {
        StatementResponse res = rentalService.generateStatement("Empty", List.of());

        assertEquals("Empty", res.customerName());
        assertEquals(0.0, res.totalAmount(), 1e-9);
        assertEquals(0, res.points());
    }

    @Test
    @DisplayName("Regular movie 2 days")
    void whenRegularMovieRentedTwoDays_thenChargeIs2_0() {
        StatementResponse res = rentalService.generateStatement("Reg",
                List.of(new RentalRequest(remboTitle, remboType, 2)));

        assertEquals("Reg", res.customerName());
        assertEquals(2.0, res.totalAmount(), 1e-9);
        assertEquals(1, res.points());
    }

    @Test
    @DisplayName("Regular movie 3 days")
    void whenRegularMovieRentedThreeDays_thenChargeIs3_5() {
        StatementResponse res = rentalService.generateStatement("Reg3",
                List.of(new RentalRequest(remboTitle, remboType, 3)));

        assertEquals("Reg3", res.customerName());
        assertEquals(3.5, res.totalAmount(), 1e-9);
        assertEquals(1, res.points());
    }

    @Test
    @DisplayName("New release 1 day")
    void whenNewReleaseRentedOneDay_thenChargeIs3_0() {
        StatementResponse res = rentalService.generateStatement("NR1",
                List.of(new RentalRequest(lotrTitle, lotrType, 1)));

        assertEquals("NR1", res.customerName());
        assertEquals(3.0, res.totalAmount(), 1e-9);
        assertEquals(1, res.points());
    }

    @Test
    @DisplayName("New release 2 days bonus points")
    void whenNewReleaseRentedTwoDays_thenGivesBonusPoint() {
        StatementResponse res = rentalService.generateStatement("NR2",
                List.of(new RentalRequest(lotrTitle, lotrType, 2)));

        assertEquals("NR2", res.customerName());
        assertEquals(6.0, res.totalAmount(), 1e-9);
        assertEquals(2, res.points());
    }

    @Test
    @DisplayName("Children's movie 2 days")
    void whenChildrensMovieRentedTwoDays_thenChargeIs1_5() {
        StatementResponse res = rentalService.generateStatement("Child2",
                List.of(new RentalRequest(hpTitle, hpType, 2)));

        assertEquals("Child2", res.customerName());
        assertEquals(1.5, res.totalAmount(), 1e-9);
        assertEquals(1, res.points());
    }

    @Test
    @DisplayName("Children's movie 4 days")
    void whenChildrensMovieRentedFourDays_thenChargeIs3_0() {
        StatementResponse res = rentalService.generateStatement("Child4",
                List.of(new RentalRequest(hpTitle, hpType, 4)));

        assertEquals("Child4", res.customerName());
        assertEquals(3.0, res.totalAmount(), 1e-9);
        assertEquals(1, res.points());
    }

    @Test
    @DisplayName("Totals for multiple rentals")
    void whenMultipleRentals_thenTotalsAndPointsCalculated() {
        StatementResponse res = rentalService.generateStatement("Multi", List.of(
                new RentalRequest(remboTitle, remboType, 3),
                new RentalRequest(lotrTitle, lotrType, 2),
                new RentalRequest(hpTitle, hpType, 4)
        ));

        assertEquals("Multi", res.customerName());
        assertEquals(12.5, res.totalAmount(), 1e-9);
        assertEquals(4, res.points());
    }
}
