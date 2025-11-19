package example.model;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  Movie
@version  1.0.0
@since 19.11.2025 - 23.47
*/

import example.strategy.Price;
import example.strategy.PriceFactory;

public class Movie {
    private final String title;
    private final MovieType type;
    private final Price price;

    public Movie(String title, MovieType type) {
        this.title = title;
        this.type = type;
        this.price = PriceFactory.create(type);
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int days) {
        return price.getCharge(days);
    }

    public MovieType getType() {
        return type;
    }

    public int getFrequentRenterPoints(int days) {
        return price.getFrequentRenterPoints(days);
    }
}