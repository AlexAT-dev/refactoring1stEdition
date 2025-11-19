package example.strategy;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  NewReleasePrice
@version  1.0.0
@since 19.11.2025 - 23.48
*/

public class NewReleasePrice extends Price {
    @Override
    public double getCharge(int days) {
        return days * 3;
    }

    @Override
    public int getFrequentRenterPoints(int days) {
        return days > 1 ? 2 : 1;
    }
}
