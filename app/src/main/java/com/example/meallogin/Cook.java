package com.example.meallogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cook extends GeneralUser implements Serializable {
    private boolean status;
    private Menu menu;
    private String suspensionDate;
    private String address;
    //region Constructors
    public Cook(){}
    
    public Cook(String Username, String Password, String Email, String address){
        this.setUsername(Username);
        this.setPassword(Password);
        this.setRole("Cook");
        this.setEmail(Email);
        this.status = false;
        this.suspensionDate = null;
        this.menu = new Menu();
        this.address = address;
    }
    //endregion


    //region Getters
    public boolean getStatus() {
        return status;
    }

    public String getSuspensionDate(){
        return this.suspensionDate;
    }

    public Menu getMenu(){return this.menu;}

    public String getAddress(){
        return this.address;
    }
    //Gonna create a Menu class for each cook, will allow for further differentiation between general users


    //endregion

    //region Setters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setMenu(Menu menu){this.menu = menu;}

    public void setSuspensionDate(String date){
        this.suspensionDate = date;
    }

    //endregion
    public boolean isSuspended(){return this.status = true;}
}
