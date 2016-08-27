package com.mash.pig.bobpercent.model;

/**
 * Created by bigstark on 2016. 8. 27..
 */
public class FoodModel {

    private int foodId;
    private String name;
    private String imageLink;

    public int getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public String toString() {
        return "FoodModel{" +
                "foodId=" + foodId +
                ", name='" + name + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
