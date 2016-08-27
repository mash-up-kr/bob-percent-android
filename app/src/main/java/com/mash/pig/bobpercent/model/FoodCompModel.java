package com.mash.pig.bobpercent.model;

/**
 * Created by bigstark on 2016. 8. 27..
 */
public class FoodCompModel {

    private int compId;
    private int firstFoodId;
    private int secondFoodId;

    private FoodModel firstFood;
    private FoodModel secondFood;

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public int getFirstFoodId() {
        return firstFoodId;
    }

    public void setFirstFoodId(int firstFoodId) {
        this.firstFoodId = firstFoodId;
    }

    public int getSecondFoodId() {
        return secondFoodId;
    }

    public void setSecondFoodId(int secondFoodId) {
        this.secondFoodId = secondFoodId;
    }

    public FoodModel getFirstFood() {
        return firstFood;
    }

    public void setFirstFood(FoodModel firstFood) {
        this.firstFood = firstFood;
    }

    public FoodModel getSecondFood() {
        return secondFood;
    }

    public void setSecondFood(FoodModel secondFood) {
        this.secondFood = secondFood;
    }

    @Override
    public String toString() {
        return "FoodCompModel{" +
                "compId=" + compId +
                ", firstFoodId=" + firstFoodId +
                ", secondFoodId=" + secondFoodId +
                ", firstFood=" + firstFood +
                ", secondFood=" + secondFood +
                '}';
    }
}
