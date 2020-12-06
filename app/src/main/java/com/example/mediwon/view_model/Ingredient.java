package com.example.mediwon.view_model;

public class Ingredient {

    String divNm;       // 분류명
    String fomnTpNm;    // 제형구분명
    String gnlNm;       // 일반명
    String gnlNmCd;     // 일반명코드
    String injcPthNm;   // 투여경로명
    String iqtyTxt;     // 함량내용
    String meftDivNo;   // 약효분류번호
    String unit;        // 단위

    public String getDivNm() {
        return divNm;
    }

    public void setDivNm(String divNm) {
        this.divNm = divNm;
    }

    public String getFomnTpNm() {
        return fomnTpNm;
    }

    public void setFomnTpNm(String fomnTpNm) {
        this.fomnTpNm = fomnTpNm;
    }

    public String getGnlNm() {
        return gnlNm;
    }

    public void setGnlNm(String gnlNm) {
        this.gnlNm = gnlNm;
    }

    public String getGnlNmCd() {
        return gnlNmCd;
    }

    public void setGnlNmCd(String gnlNmCd) {
        this.gnlNmCd = gnlNmCd;
    }

    public String getInjcPthNm() {
        return injcPthNm;
    }

    public void setInjcPthNm(String injcPthNm) {
        this.injcPthNm = injcPthNm;
    }

    public String getIqtyTxt() {
        return iqtyTxt;
    }

    public void setIqtyTxt(String iqtyTxt) {
        this.iqtyTxt = iqtyTxt;
    }

    public String getMeftDivNo() {
        return meftDivNo;
    }

    public void setMeftDivNo(String meftDivNo) {
        this.meftDivNo = meftDivNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
