package com.company;

import java.io.Serializable;

public class Pear extends Tree implements Serializable {

    public Pear() {
        this.age = 0;
        this.fructification = 0;
        this.type = Type.APPLE;
    }

    public Pear(int age, int fructification){super( age, fructification, Type.PEAR);}
}

