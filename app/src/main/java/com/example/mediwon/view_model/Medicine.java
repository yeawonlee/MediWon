package com.example.mediwon.view_model;

public class Medicine {

    //String itemName;            // 품목명
    //String entpriseName;        // 업체명
    //String specialtyPublic;     // 전문/일반 구분
    //String productType;         // 분류명
    String itemIngredientName;        // 주성분
    String materialName;              // 원료성분
    String effectDocID;               // 효능효과
    String usageDocID;                // 용법용량
    String notaBeneDocID;             // 주의사항
    String storageMethod;             // 저장방법
//    StringBuffer effectDocData;             // 효능효과 문서 데이터
    String usageDocData;              // 용법용량 문서 데이터
    String notaBeneDocData;           // 주의사항 문서 데이터
    String precautionNoteDocData;     // 주의사항(전문) 문서 데이터
    String mainItemIngredient;        // 유효성분
    String ingredientName;            // 첨가제

    public String getItemIngredientName() {
        return itemIngredientName;
    }

    public void setItemIngredientName(String itemIngredientName) {
        this.itemIngredientName = itemIngredientName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getEffectDocID() {
        return effectDocID;
    }

    public void setEffectDocID(String effectDocID) {
        this.effectDocID = effectDocID;
    }

    public String getUsageDocID() {
        return usageDocID;
    }

    public void setUsageDocID(String usageDocID) {
        this.usageDocID = usageDocID;
    }

    public String getNotaBeneDocID() {
        return notaBeneDocID;
    }

    public void setNotaBeneDocID(String notaBeneDocID) {
        this.notaBeneDocID = notaBeneDocID;
    }

    public String getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }
/*
    public StringBuffer getEffectDocData() {
        return effectDocData;
    }

    public void setEffectDocData(StringBuffer effectDocData) {
        this.effectDocData = effectDocData;
    }
*/
    public String getUsageDocData() {
        return usageDocData;
    }

    public void setUsageDocData(String usageDocData) {
        this.usageDocData = usageDocData;
    }

    public String getNotaBeneDocData() {
        return notaBeneDocData;
    }

    public void setNotaBeneDocData(String notaBeneDocData) {
        this.notaBeneDocData = notaBeneDocData;
    }

    public String getPrecautionNoteDocData() {
        return precautionNoteDocData;
    }

    public void setPrecautionNoteDocData(String precautionNoteDocData) {
        this.precautionNoteDocData = precautionNoteDocData;
    }

    public String getMainItemIngredient() {
        return mainItemIngredient;
    }

    public void setMainItemIngredient(String mainItemIngredient) {
        this.mainItemIngredient = mainItemIngredient;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
