package example.strategy;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  RegularPrice
@version  1.0.0
@since 19.11.2025 - 23.48
*/

public class RegularPrice extends Price {
    @Override
    public double getCharge(int days) {
        double result = 2;
        if (days > 2) {
            result += (days - 2) * 1.5;
        }
        return result;
    }
}

