package example;

import java.util.List;

class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        double totalAmount = 0;
        int frequentRenterPoints = 0;

        for (Rental each : rentals) {
            double thisAmount = each.getMovie().getCharge(each.getDaysRented());
            frequentRenterPoints += each.getMovie().getFrequentRenterPoints(each.getDaysRented());

            result.append("\t").append(each.getMovie().getTitle())
                    .append("\t").append(thisAmount).append("\n");

            totalAmount += thisAmount;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

        return result.toString();
    }
}
