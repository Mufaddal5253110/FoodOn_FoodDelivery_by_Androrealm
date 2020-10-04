package com.food_on.app.DeliveryFoodPanel;

public class DeliveryShipOrders {

    private String ChefId,DishId,DishName,DishPrice,DishQuantity,RandomUID,TotalPrice,UserId;

    public DeliveryShipOrders(String chefId, String dishId, String dishName, String dishPrice, String dishQuantity, String randomUID, String totalPrice, String userId) {
        ChefId = chefId;
        DishId = dishId;
        DishName = dishName;
        DishPrice = dishPrice;
        DishQuantity = dishQuantity;
        RandomUID = randomUID;
        TotalPrice = totalPrice;
        UserId = userId;
    }

    public DeliveryShipOrders()
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

    public String getDishPrice() {
        return DishPrice;
    }

    public void setDishPrice(String dishPrice) {
        DishPrice = dishPrice;
    }

    public String getDishQuantity() {
        return DishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        DishQuantity = dishQuantity;
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
