package com.food_on.app.ChefFoodPanel;

public class FoodSupplyDetails {

    public String Dishes,Quantity,Price,Description,ImageURL,RandomUID,ChefId;

    public FoodSupplyDetails(String dishes, String quantity, String price, String description, String imageURL,String randomUID,String chefId) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID=randomUID;
        ChefId=chefId;
    }

}
