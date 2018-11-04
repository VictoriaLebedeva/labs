package com.company;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class Bill implements Serializable {

    Date date = new Date();

    private String company;
    private String service;
    private String unit = "unit";
    private double cost;
    private int volume;


    public Bill(String company, String service, double cost, int volume) {
        assert (company == null || service == null || cost < 0 || volume < 0 );
        this.company = company;
        this.service = service;
        this.cost = cost;
        this.volume = volume;
    }

    public Object getFieldName(String fieldName)  throws IllegalAccessException, NoSuchFieldException {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(this);
    }


    @Override
    public String toString() {
        return "Bill{" +
                "date = " + date +
                ", company = '" + company + '\'' +
                ", service = '" + service + '\'' +
                ", unit = '" + unit + '\'' +
                ", cost = " + cost +
                ", volume = " + volume +
                '}';
    }
}
