package com.food_on.app.ChefFoodPanel;

public class ChefPendingOrders {

    private String ChefId,DishId,DishName,DishQuantity,Price,RandomUID,TotalPrice,UserId;

    public ChefPendingOrders(String chefId, String dishId, String dishName, String dishQuantity, String price,String randomUID, String totalPrice, String userId) {
        ChefId = chefId;
        DishId = dishId;
        DishName = dishName;
        DishQuantity = dishQuantity;
        Price = price;
        RandomUID=randomUID;
        TotalPrice = totalPrice;
        UserId = userId;
    }

    public ChefPendingOrders()
    {

    }

    public String getChefId() {
        return ChefId;
    }

    public void setChefId(String chefId) {
        ChefId = chefId;
    }

    public String getDishId() {
        return DishId;
    }

    public void setDishId(String dishId) {
        DishId = dishId;
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

    public String getRandomUID() {
        return RandomUID;
    }

    public void setRandomUID(String randomUID) {
        RandomUID = randomUID;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
