package example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static example.Movie.MovieType.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Movie REMBO;
    private Movie LOTR;
    private Movie HARRY_POTTER;

    @BeforeEach
    void setUp() {
        REMBO = new Movie("Rembo", REGULAR);
        LOTR = new Movie("Lord of the Rings", NEW_RELEASE);
        HARRY_POTTER = new Movie("Harry Potter", CHILDRENS);
    }

    @Test
    void whenStatementRequestedWithMixedRentals_thenReturnsFormattedStatement() {

        List<Rental> rentals = List.of(
                new Rental(REMBO, 1),
                new Rental(LOTR, 4),
                new Rental(HARRY_POTTER, 5)
        );

        Customer customer = new Customer("Alex AT", rentals);

        String expected = "Rental Record for Alex AT\n" +
                "\tRembo\t2.0\n" +
                "\tLord of the Rings\t12.0\n" +
                "\tHarry Potter\t4.5\n" +
                "Amount owed is 18.5\n" +
                "You earned 4 frequent renter points";
        assertEquals(expected, customer.statement());
    }

    @Test
    void whenRegularMovieRentedManyDays_thenChargeIsCalculatedCorrectly() {
        Customer c = new Customer("LongRent", List.of(new Rental(REMBO, 10)));

        String expected = "Rental Record for LongRent\n" +
                "\tRembo\t14.0\n" +
                "Amount owed is 14.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenNoRentals_thenStatementShowsZeroAmounts() {
        Customer c = new Customer("Empty", List.of());
        String expected = "Rental Record for Empty\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenRegularMovieRentedTwoDays_thenChargeIs2_0() {
        Customer c = new Customer("Reg", List.of(new Rental(REMBO, 2)));
        String expected = "Rental Record for Reg\n" +
                "\tRembo\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenRegularMovieRentedThreeDays_thenChargeIs3_5() {
        Customer c = new Customer("Reg3", List.of(new Rental(REMBO, 3)));
        String expected = "Rental Record for Reg3\n" +
                "\tRembo\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenNewReleaseRentedOneDay_thenChargeIs3_0() {
        Customer c = new Customer("NR1", List.of(new Rental(LOTR, 1)));
        String expected = "Rental Record for NR1\n" +
                "\tLord of the Rings\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenNewReleaseRentedTwoDays_thenGivesBonusPoint() {
        Customer c = new Customer("NR2", List.of(new Rental(LOTR, 2)));
        String expected = "Rental Record for NR2\n" +
                "\tLord of the Rings\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenChildrensMovieRentedTwoDays_thenChargeIs1_5() {
        Customer c = new Customer("Child2", List.of(new Rental(HARRY_POTTER, 2)));
        String expected = "Rental Record for Child2\n" +
                "\tHarry Potter\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenChildrensMovieRentedFourDays_thenChargeIs3_0() {
        Customer c = new Customer("Child4", List.of(new Rental(HARRY_POTTER, 4)));
        String expected = "Rental Record for Child4\n" +
                "\tHarry Potter\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";
        assertEquals(expected, c.statement());
    }

    @Test
    void whenMultipleRentals_thenTotalsAndPointsCalculated() {
        Customer c = new Customer("Multi", List.of(
                new Rental(REMBO, 3),         // 3.5
                new Rental(LOTR, 2),          // 6.0 (2 points)
                new Rental(HARRY_POTTER, 4)   // 3.0
        ));
        String expected = "Rental Record for Multi\n" +
                "\tRembo\t3.5\n" +
                "\tLord of the Rings\t6.0\n" +
                "\tHarry Potter\t3.0\n" +
                "Amount owed is 12.5\n" +
                "You earned 4 frequent renter points";
        assertEquals(expected, c.statement());
    }
}