package com.example.forfoodiesbyfoodies;

public class RestaurantClass {
    String restaurantName;
    String restaurantType;
    int image;
    int star1;
    int star2;
    int star3;
    int star4;
    int starHalf;
    int btnReadMore;


    public RestaurantClass(String restaurantName, String restaurantType, int image,
                           int star1, int star2, int star3, int star4, int starHalf, int btnReadMore) {
        this.restaurantName = restaurantName;
        this.restaurantType = restaurantType;
        this.image = image;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.starHalf = starHalf;
        this.btnReadMore = btnReadMore;


    }

    public String getRestaurantName() {return restaurantName;}

    public String getRestaurantType() {return restaurantType;}

    public int getImage() {return image;}

    public int getStar1() {return star1;}

    public int getStar2() {return star2;}

    public int getStar3() {return star3;}

    public int getStar4() {return star4;}

    public int getStar5() {return star4;}

    public int getStarHalf() {return starHalf;}

    public int getBtnReadMore(){return btnReadMore;}
}
