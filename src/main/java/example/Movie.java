package example;

public class Movie {
    private final String title;
    private final Price price;

    public Movie(String title, MovieType type) {
        this.title = title;
        this.price = Price.of(type);
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }

    public enum MovieType {
        REGULAR, NEW_RELEASE, CHILDRENS
    }
}
