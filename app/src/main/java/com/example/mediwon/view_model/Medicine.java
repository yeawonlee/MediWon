package com.example.mediwon.view_model;

public class Medicine {

    String itemIngredientName;        // 주성분
    String materialName;              // 원료성분
/*
    StringBuffer effectDocData;             // 효능효과 문서 데이터
    StringBuffer usageDocData;              // 용법용량 문서 데이터
    StringBuffer notaBeneDocData;           // 주의사항 문서 데이터
    StringBuffer precautionNoteDocData;     // 주의사항(전문) 문서 데이터
*/

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

/*
    public StringBuffer getEffectDocData() {
        return effectDocData;
    }

    public void setEffectDocData(StringBuffer effectDocData) {
        this.effectDocData = effectDocData;
    }

    public StringBuffer getUsageDocData() {
        return usageDocData;
    }

    public void setUsageDocData(StringBuffer usageDocData) {
        this.usageDocData = usageDocData;
    }

    public StringBuffer getNotaBeneDocData() {
        return notaBeneDocData;
    }

    public void setNotaBeneDocData(StringBuffer notaBeneDocData) {
        this.notaBeneDocData = notaBeneDocData;
    }

    public StringBuffer getPrecautionNoteDocData() {
        return precautionNoteDocData;
    }

    public void setPrecautionNoteDocData(StringBuffer precautionNoteDocData) {
        this.precautionNoteDocData = precautionNoteDocData;
    }
*/

}
