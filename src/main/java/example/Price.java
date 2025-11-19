package example;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  Price
@version  1.0.0
@since 19.11.2025 - 23.15
*/

abstract class Price {
    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented) {
        return 1; // default
    }

    static Price of(Movie.MovieType type) {
        return switch (type) {
            case REGULAR -> new RegularPrice();
            case NEW_RELEASE -> new NewReleasePrice();
            case CHILDRENS -> new ChildrensPrice();
        };
    }
}

class RegularPrice extends Price {
    @Override
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
}

class NewReleasePrice extends Price {
    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}

class ChildrensPrice extends Price {
    @Override
    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}
