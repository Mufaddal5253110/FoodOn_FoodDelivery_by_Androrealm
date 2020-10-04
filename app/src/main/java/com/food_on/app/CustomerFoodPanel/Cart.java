package com.food_on.app.CustomerFoodPanel;

public class Cart {

    private String ChefId,DishID,DishName,DishQuantity,Price,Totalprice;

    public Cart(String chefId, String dishID, String dishName, String dishQuantity, String price, String totalprice) {
        ChefId = chefId;
        DishID = dishID;
        DishName = dishName;
        DishQuantity = dishQuantity;
        Price = price;
        Totalprice = totalprice;
    }

    public Cart() {
    }

    public String getChefId() {
        return ChefId;
    }

    public void setChefId(String chefId) {
        ChefId = chefId;
    }

    public String getDishID() {
        return DishID;
    }

    public void setDishID(String dishID) {
        DishID = dishID;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public String getDishQuantity() {
        return DishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        DishQuantity = dishQuantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(String totalprice) {
        Totalprice = totalprice;
    }
}
