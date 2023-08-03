package com.alinaharkevich;

public class Order {
    //Multi-aspect inheritance
    private FoodItem food;
    private DrinkItem drink;
    private Waiter waiter;

    public Order(FoodItem food, DrinkItem drink, Waiter waiter) {
        setFood(food);
        setDrink(drink);
        setWaiter(waiter);
    }

    public double getTotalPrice() {
        return food.getPrice() + drink.getPrice();
    }

    public String getCuisine() {
        return food.getCuisine();
    }

    public double getAlcoholPersentage() {
        return drink.getAlcoholPersentage();
    }

    public String getWaiterName() {
        return waiter.getName();
    }

    public void setFood(FoodItem food) {
        if (food == null) {
            throw new IllegalArgumentException("Food item cannot be null.");
        }
        this.food = food;
    }

    public void setDrink(DrinkItem drink) {
        if (drink == null) {
            throw new IllegalArgumentException("Drink item cannot be null.");
        }
        this.drink = drink;
    }

    public void setWaiter(Waiter waiter) {
        if (waiter == null) {
            throw new IllegalArgumentException("Waiter cannot be null.");
        }
        this.waiter = waiter;
    }

    @Override
    public String toString() {
        return "Order: " + food.getName() + " (" + food.getCuisine() + ") "
                + "and " + drink.getName() + " (" + drink.getAlcoholPersentage() + "% ABV), "
                + "served by " + waiter.getName() + ", "
                + "Total Price: $" + getTotalPrice();
    }

}
