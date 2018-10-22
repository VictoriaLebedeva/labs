package com.company;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Date;

public class Bill implements Serializable {

    Date date = new Date();

    String company;
    String service;
    String unit = "unit";
    double cost;
    int volume;

   public Bill(){
       company = null;
       service = null;
       cost = 0;
       volume = 0;
   }

    public Bill(String company, String service, double cost, int volume) {
        assert (company == null || service == null || cost < 0 || volume < 0 );
        this.company = company;
        this.service = service;
        this.cost = cost;
        this.volume = volume;
    }

    public static Bill read( Scanner fin ) {
        Bill bill = new Bill();
        bill.company = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.service = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.cost = fin.nextDouble();
        if ( ! fin.hasNextLine()) return null;
        bill.volume = fin.nextInt();
        return bill;
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
