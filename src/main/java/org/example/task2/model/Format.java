package org.example.task2.model;

public enum Format {
    PAPERBACK("Paperback"),
    KINDLE("Kindle"),
    LIBRARY_BINDING("Library Binding"),
    AUDIBLE_AUDIOBOOK("Audible Audiobook"),
    MASS_MARKET_PAPERBACK("Mass Market Paperback"),
    HARDCOVER("Hardcover"),
    MAGAZINE("Magazine"),
    N_A("Not available");

    public final String value;

    private Format(String label) {
        this.value = label;
    }

    public static Format getByValue(String value) {
        Format bookType = null;
        for (Format type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                bookType = type;
                break;
            }
        }

        return bookType;
    }

    public String getValue() {
        return value;
    }
}
