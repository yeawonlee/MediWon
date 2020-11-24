package com.example.mediwon.view_model;

public class Medicine {

    int image;
    String name;
    String pharmaceuticalCompany;

    public Medicine(int image, String name, String pharmaceuticalCompany) {
        this.image = image;
        this.name = name;
        this.pharmaceuticalCompany = pharmaceuticalCompany;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPharmaceuticalCompany() {
        return pharmaceuticalCompany;
    }

}
