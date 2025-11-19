# Refactoring Summary

This document describes the refactoring performed on the original Movie Rental code.  
The goal was to improve structure, readability, flexibility and remove duplicate logic.

## Changes

1. **Moved price calculation logic out of `Customer`**  
   Previously, `Customer` handled pricing with a long `switch` statement.  
   Now this logic is encapsulated inside dedicated strategy classes.

2. **Removed the `switch` statement based on movie type**  
   Pricing is now handled through polymorphism instead of conditional logic.

3. **Introduced the Strategy Pattern for pricing**  
   A new abstract class `Price` defines the pricing interface,  
   while `RegularPrice`, `NewReleasePrice`, and `ChildrensPrice` implement specific rules.

4. **Added the `Price` class**  
   This class is responsible for:
    - calculating rental charges (`getCharge`)
    - awarding frequent renter points (`getFrequentRenterPoints`)
    - providing correct strategy via `Price.of()` factory method

5. **Updated the `Movie` class to use a `Price` object instead of enum-only logic**  
   `MovieType` remains, but now it simply selects the correct pricing strategy on creation.

6. **Simplified and cleaned the `Customer` class**  
   It now only aggregates results and builds the statement output,  
   without performing business calculations.

7. **Improved extensibility of the system**  
   Adding a new movie type now only requires creating a new subclass of `Price`  
   and registering it in the factory method.

8. **Replaced string concatenation with `StringBuilder`**  
   This optimizes performance in the `statement()` method.

9. **Moved charge and points logic closer to the data**  
   These methods now live in `Movie` and `Price`, improving encapsulation and cohesion.

# Test results:

![Test Results](src/main/resources/images/test-results.png)