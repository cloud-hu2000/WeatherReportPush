package com.CloudHu.weather.entity;

public class UserWeather {
    private String phone;
    private String province;
    private String city;

    public UserWeather(String phone, String province, String city) {
        this.phone = phone;
        this.province = province;
        this.city = city;
    }

    public UserWeather() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
