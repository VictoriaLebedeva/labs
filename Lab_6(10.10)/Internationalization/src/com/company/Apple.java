package com.company;

import java.io.Serializable;

public class Apple extends Tree implements Serializable {

    public Apple() {
        this.age = 0;
        this.fructification = 0;
        this.type = Type.APPLE;
    }

    public Apple(int age, int fructification){super( age, fructification, Tree.Type.APPLE);}
}

