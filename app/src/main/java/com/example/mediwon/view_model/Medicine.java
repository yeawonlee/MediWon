package com.example.mediwon.view_model;

public class Medicine {

    String itemName;            // 품목명
    String entpriseName;        // 업체명
    String specialtyPublic;     // 전문/일반 구분
    String productType;         // 분류명
    String itemIngredientName;  // 주성분

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getEntpriseName() {
        return entpriseName;
    }

    public void setEntpriseName(String entpriseName) {
        this.entpriseName = entpriseName;
    }

    public String getSpecialtyPublic() {
        return specialtyPublic;
    }

    public void setSpecialtyPublic(String specialtyPublic) {
        this.specialtyPublic = specialtyPublic;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getItemIngredientName() {
        return itemIngredientName;
    }

    public void setItemIngredientName(String itemIngredientName) {
        this.itemIngredientName = itemIngredientName;
    }
}
