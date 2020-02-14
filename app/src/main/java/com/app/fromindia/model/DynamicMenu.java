package com.app.fromindia.model;

public class DynamicMenu {

    String MenuName = "";

    int MenuID = 0;

    int MenuImage = 0;

    public String getQty() {
        return Qty;
    }

    public void setQty(String aQty) {
        this.Qty = aQty;
    }

    String Qty = "1";

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    String Price = "";

    public Boolean getCheckAddValue() {
        return CheckAddValue;
    }

    public void setCheckAddValue(Boolean checkAddValue) {
        CheckAddValue = checkAddValue;
    }

    Boolean CheckAddValue = false;


    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    boolean isCheck = false;

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int menuID) {
        MenuID = menuID;
    }

    public int getMenuImage() {
        return MenuImage;
    }

    public void setMenuImage(int menuImage) {
        MenuImage = menuImage;
    }
}
