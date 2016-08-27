package com.mash.pig.bobpercent.model;

/**
 * Created by bigstark on 2016. 8. 27..
 */

public class ErrorModel {

    private String message;

    public ErrorModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "message='" + message + '\'' +
                '}';
    }
}
