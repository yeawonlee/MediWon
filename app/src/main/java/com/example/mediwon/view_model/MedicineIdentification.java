package com.example.mediwon.view_model;

public class MedicineIdentification {

    String imageUrl;    // 큰 제품 이미지
    String name;    // 품목명
    String enterprise;  // 업체명

    String itemSeq;    // 품목 일련번호
    String chart;   // 성상
    String drug_shape;  // 의약품 모양
    String color_class1;    // 색깔(앞)
    String classNo;    // 분류번호
    String className;   // 분류명
    String etcOtcName;  // 구분 (전문/일반)
    String fromCodeName;    // 제형코드이름
    String markCodeFrontImg;    // 마크 이미지(앞)
    String engName; // 제품영문명
    String ediCode; // 보험코드

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

    public String getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(String itemSeq) {
        this.itemSeq = itemSeq;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public String getDrug_shape() {
        return drug_shape;
    }

    public void setDrug_shape(String drug_shape) {
        this.drug_shape = drug_shape;
    }

    public String getColor_class1() {
        return color_class1;
    }

    public void setColor_class1(String color_class1) {
        this.color_class1 = color_class1;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEtcOtcName() {
        return etcOtcName;
    }

    public void setEtcOtcName(String etcOtcName) {
        this.etcOtcName = etcOtcName;
    }

    public String getFromCodeName() {
        return fromCodeName;
    }

    public void setFromCodeName(String fromCodeName) {
        this.fromCodeName = fromCodeName;
    }

    public String getMarkCodeFrontImg() {
        return markCodeFrontImg;
    }

    public void setMarkCodeFrontImg(String markCodeFrontImg) {
        this.markCodeFrontImg = markCodeFrontImg;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getEdiCode() {
        return ediCode;
    }

    public void setEdiCode(String ediCode) {
        this.ediCode = ediCode;
    }
}
