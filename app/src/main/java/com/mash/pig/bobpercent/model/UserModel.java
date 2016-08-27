package com.mash.pig.bobpercent.model;

import java.util.Date;

/**
 * Created by bigstark on 2016. 8. 27..
 */
public class UserModel {

    private int userId;
    private String name;
    private String email;
    private String password;
    private String code;
    private boolean pending;
    private Date createDate;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (userId != userModel.userId) return false;
        if (pending != userModel.pending) return false;
        if (name != null ? !name.equals(userModel.name) : userModel.name != null) return false;
        if (email != null ? !email.equals(userModel.email) : userModel.email != null) return false;
        if (password != null ? !password.equals(userModel.password) : userModel.password != null) return false;
        if (code != null ? !code.equals(userModel.code) : userModel.code != null) return false;
        return createDate != null ? createDate.equals(userModel.createDate) : userModel.createDate == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (pending ? 1 : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
