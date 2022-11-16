package com.example.forfoodiesbyfoodies;

public class RestaurantClass {
    String restaurantName;
    String restaurantReview;
    String restaurantRate;
    int image;

    public RestaurantClass(String restaurantName, String restaurantReview, String restaurantRate, int image) {
        this.restaurantName = restaurantName;
        this.restaurantReview = restaurantReview;
        this.restaurantRate = restaurantRate;
        this.image = image;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantReview() {
        return restaurantReview;
    }

    public String getRestaurantRate() {
        return restaurantRate;
    }

    public int getImage() {
        return image;
    }
}
