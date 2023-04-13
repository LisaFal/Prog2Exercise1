package at.ac.fhcampuswien.fhmdb.models;

public enum Rating {
    FROM4(4.0), FROM5(5.0), FROM6(6.0), FROM7(7.0), FROM8(8.0), FROM9(9.0);

    Rating (double rating) {
        this.rating = rating;
    }

    private double rating;

    @Override
    public String toString() {
        return Double.toString(rating);
    }
}
