package example.dto;


/*
@author   AlexAT
@project   refactoring1stEdition
@class  RentalRequest
@version  1.0.0
@since 19.11.2025 - 23.49
*/

import example.model.MovieType;

public record RentalRequest(String title, MovieType type, int days) {}

