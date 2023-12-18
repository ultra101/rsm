package org.example.task2.model;

public class Product {
    private final Format format;
    private final Float price;
    private final String title;

    public Product(String title, Format type, Float price) {
        this.title = title;
        this.format = type;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Format getFormat() {
        return format;
    }

    public Float getPrice() {
        return price;
    }
}
