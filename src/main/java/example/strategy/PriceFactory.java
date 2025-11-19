package example.strategy;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  PriceFactory
@version  1.0.0
@since 19.11.2025 - 23.48
*/

import example.model.MovieType;

public class PriceFactory {
    public static Price create(MovieType type) {
        return switch (type) {
            case REGULAR -> new RegularPrice();
            case NEW_RELEASE -> new NewReleasePrice();
            case CHILDRENS -> new ChildrensPrice();
        };
    }
}
