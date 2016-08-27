package com.mash.pig.bobpercent.model;

import java.util.Date;
import java.util.List;

/**
 * Created by bigstark on 2016. 8. 27..
 */
public class GameModel {

    private int gameId;
    private String code;
    private Date createDate;

    private List<FoodCompModel> comps;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<FoodCompModel> getComps() {
        return comps;
    }

    public void setComps(List<FoodCompModel> comps) {
        this.comps = comps;
    }

    @Override
    public String toString() {
        return "GameModel{" +
                "gameId=" + gameId +
                ", code='" + code + '\'' +
                ", createDate=" + createDate +
                ", comps=" + comps +
                '}';
    }
}
