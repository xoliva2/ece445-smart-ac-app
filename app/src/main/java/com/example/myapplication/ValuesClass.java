package com.example.myapplication;

public class ValuesClass {
    private int manualKnobSetting;      // 1: hiCool 2: loCool 3: hiFan 0: off
    private int manualTempSetting;      // takes temperature in Fahrenheit
    private int scheduleKnobSetting;    // 1: hiCool 2: loCool 3: hiFan 0: off
    private int scheduleTempSetting;    // takes temperature in Fahrenheit
    private int scheduleTempTime;       // takes single integer containing start and end

    public ValuesClass(int temp){
        manualKnobSetting = 0;
        manualTempSetting = 0;
        scheduleKnobSetting = 0;
        scheduleTempSetting = 0;
        scheduleTempTime = 0;

    }

    public void setManualKnobSetting(int temp) {
        this.manualKnobSetting = temp;
    }

    public void setManualTempSetting(int temp) {
        this.manualTempSetting = temp;
    }

    public void setScheduleKnobSetting(int temp) {
        this.scheduleKnobSetting = temp;
    }

    public void setScheduleTempSetting(int temp) {
        this.scheduleTempSetting = temp;
    }

    public void setScheduleTempTime(int temp) {
        this.scheduleTempTime = temp;
    }

    public int getManualKnobSetting() {
        return manualKnobSetting;
    }

    public int getManualTempSetting() {
        return manualTempSetting;
    }

    public int getScheduleKnobSetting() {
        return scheduleKnobSetting;
    }

    public int getScheduleTempSetting() {
        return scheduleTempSetting;
    }

    public int getScheduleTempTime() {
        return scheduleTempTime;
    }

}
