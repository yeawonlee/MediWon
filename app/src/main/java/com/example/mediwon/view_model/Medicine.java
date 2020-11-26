package com.example.mediwon.view_model;

public class Medicine {

    String imageUrl;
    String name;
    String enterprise;

    /*
    public Medicine(int image, String name, String enterprise) {
        this.image = image;
        this.name = name;
        this.enterprise = enterprise;
    }
    */

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }
}
