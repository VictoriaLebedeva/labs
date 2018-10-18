package com.company;

import java.io.Serializable;

public class Cherry extends Tree implements Serializable {

    public Cherry() {
        this.age = 0;
        this.fructification = 0;
        this.type = Type.APPLE;
    }

    public Cherry(int age, int fructification){super( age, fructification, Type.CHERRY);}
}

