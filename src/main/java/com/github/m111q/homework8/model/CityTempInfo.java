package com.github.m111q.homework8.model;

import javax.persistence.*;

@Entity
@Table(name = "weatherforecasts")
public class CityTempInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cityName;
    private double temp;
    private String measurementTime;

    public CityTempInfo() {
    }

    public CityTempInfo(String cityName, double temp, String measurementTime) {
        this.cityName = cityName;
        this.temp = temp;
        this.measurementTime = measurementTime;
    }

    public long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    @Override
    public String toString() {
        return "CityTempInfo{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", temp=" + temp +
                ", measurementTime='" + measurementTime + '\'' +
                '}';
    }
}
