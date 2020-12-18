package com.example.mediwon.view_model;

public class SideEffect {

    String name;                        // 품명
    String name_eng;                    // 품명 (영문)
    String components;                  // 구성제제
    String period;                      // 기간
    String signal_kor;                  // 실마리정보 (국문)
    String signal_eng;                  // 실마리정보 (영문)
    String elucidatoryNotes;            // 비고
    String signalGuide;                 // 실마리정보안내
    String monitoring;                  // 지속적모니터링안내
    String permissionChangeGuide;       // 허가사항변경안내

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_eng() {
        return name_eng;
    }

    public void setName_eng(String name_eng) {
        this.name_eng = name_eng;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSignal_kor() {
        return signal_kor;
    }

    public void setSignal_kor(String signal_kor) {
        this.signal_kor = signal_kor;
    }

    public String getSignal_eng() {
        return signal_eng;
    }

    public void setSignal_eng(String signal_eng) {
        this.signal_eng = signal_eng;
    }

    public String getElucidatoryNotes() {
        return elucidatoryNotes;
    }

    public void setElucidatoryNotes(String elucidatoryNotes) {
        this.elucidatoryNotes = elucidatoryNotes;
    }

    public String getSignalGuide() {
        return signalGuide;
    }

    public void setSignalGuide(String signalGuide) {
        this.signalGuide = signalGuide;
    }

    public String getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(String monitoring) {
        this.monitoring = monitoring;
    }

    public String getPermissionChangeGuide() {
        return permissionChangeGuide;
    }

    public void setPermissionChangeGuide(String permissionChangeGuide) {
        this.permissionChangeGuide = permissionChangeGuide;
    }
}
