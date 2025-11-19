package example.strategy;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  Price
@version  1.0.0
@since 19.11.2025 - 23.48
*/

public abstract class Price {
    public abstract double getCharge(int days);

    public int getFrequentRenterPoints(int days) {
        return 1;
    }
}
