package com.company;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public abstract class Tree implements Serializable {

    protected int age;
    protected int fructification;
    protected final int ID;
    public static int count = 0;

    public enum Type { CHERRY, APPLE, PLUM, PEAR };
    protected Type type;
    public Type getType() {
        return type;
    }
    public void setType(Type type) { this.type = type; }

    public enum Status {REPLANT, KEEP};
    protected Status status;

    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, Internationalization.getLocale());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    public Tree () {
        age = 0;
        fructification = 0;
        ID = ++count;
    }

    public Tree (int age, int fructification, Type type) {
        assert (age > 0);
        this.age = age;
        if (age < 5)
            this.status = Status.REPLANT;
        else
            this.status = Status.KEEP;
        this.fructification = fructification;
        this.type = type;
        ID = ++count;
    }

    public int getAge() { return age;}
    public int getFructation() { return fructification;}
    public int getID(){return ID;}

    public void setAge(int age) {
        this.age = age;
    }

    public void setFructification(int fructification) {
        this.fructification = fructification;
    }

    public String toString () {
        return new String(Internationalization.getString(Internationalization.Tree) + " : "
                + Internationalization.getString(Internationalization.ID) + " - " + ID + " , "
                + Internationalization.getString(Internationalization.age)  + " - " +  age + " , "
                + Internationalization.getString(Internationalization.fructification) + " - "  + fructification  + " , "
                + Internationalization.getString(Internationalization.type)  + " - " + Internationalization.getString(type.toString()) + " , "
                + Internationalization.getString(Internationalization.status)+ " - "  + Internationalization.getString(status.toString()) + " , "
                + Internationalization.getString(Internationalization.creation) + " - " + getCreationDate());
    }
}



