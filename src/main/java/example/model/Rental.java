package example.model;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  Rental
@version  1.0.0
@since 19.11.2025 - 23.47
*/

public class Rental {
    private final Movie movie;
    private final int days;

    public Rental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }
}