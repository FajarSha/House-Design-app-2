package com.example.housedesign.Modal;

public class HouseModal {
    String Type;
    String Subtype;
    int ImageValue;

    public HouseModal(String type, String subtype, int imageValue) {
        Type = type;
        Subtype = subtype;
        ImageValue = imageValue;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSubtype() {
        return Subtype;
    }

    public void setSubtype(String subtype) {
        Subtype = subtype;
    }

    public int getImageValue() {
        return ImageValue;
    }

    public void setImageValue(int imageValue) {
        ImageValue = imageValue;
    }
}
