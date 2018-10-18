package com.company;

import java.io.Serializable;

public class Plum extends Tree implements Serializable {

    public Plum() {
        this.age = 0;
        this.fructification = 0;
        this.type = Type.APPLE;
    }

    public Plum(int age, int fructification){super( age, fructification, Type.PLUM);}
}

