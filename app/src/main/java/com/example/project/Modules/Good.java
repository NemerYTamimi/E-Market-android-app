package com.example.project.Modules;

public class Good {
    private String title;
    private String type;
    private String description;
    private String imageurl;
    private int height;
    private int width;
    private double price;
    private int rating;
    public Good(String title, String type, String description, String imageurl, int height, int width, double price, int rating) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.imageurl = imageurl;
        this.height = height;
        this.width = width;
        this.price = price;
        this.rating = rating;
    }

    public Good() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "good{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }

}
