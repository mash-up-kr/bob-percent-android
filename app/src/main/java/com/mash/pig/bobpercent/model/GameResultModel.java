package com.mash.pig.bobpercent.model;

import java.util.List;

/**
 * Created by bigstark on 2016. 8. 28..
 */

public class GameResultModel {
    private List<FoodModel> selectFoodItems;
    private float percent;

    public List<FoodModel> getSelectFoodItems() {
        return selectFoodItems;
    }

    public void setSelectFoodItems(List<FoodModel> selectFoodItems) {
        this.selectFoodItems = selectFoodItems;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }


    @Override
    public String toString() {
        return "GameResultModel{" +
                "selectFoodItems=" + selectFoodItems +
                ", percent=" + percent +
                '}';
    }
}
