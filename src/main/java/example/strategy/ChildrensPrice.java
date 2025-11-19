package example.strategy;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  ChildrensPrice
@version  1.0.0
@since 19.11.2025 - 23.48
*/

public class ChildrensPrice extends Price {
    @Override
    public double getCharge(int days) {
        double result = 1.5;
        if (days > 3) {
            result += (days - 3) * 1.5;
        }
        return result;
    }
}
