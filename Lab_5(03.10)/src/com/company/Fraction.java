package com.company;


import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

class Fraction implements Comparable<Fraction>, Iterator<Integer> {

    int nominator;
    int denominator;

    public Fraction() {
        nominator = 0;
        denominator = 0;
    }

    public Fraction(int nominator1, int denominator1) {
        assert (denominator != 0);
        nominator = nominator1;
        denominator = denominator1;
    }

    public Fraction (String string){
        assert (string == null);
        StringTokenizer tokens = new StringTokenizer(string,",");
        while (tokens.hasMoreTokens())
        {
            nominator = Integer.parseInt(tokens.nextToken());
            denominator = Integer.parseInt(tokens.nextToken());
        }

    }

    public void setnominator(int newnominator){
        this.nominator = newnominator;
    }

    public void setdenominator(int newdenominator){
        this.denominator = newdenominator;
    }

    public void print() {
        System.out.println("Nominator = " + this.nominator + " , denominator = " + this.denominator);
    }

    public Fraction multiply(Fraction fraction) {
        Fraction result = new Fraction();
        result.nominator = this.nominator * fraction.nominator;
        result.denominator = this.denominator * fraction.denominator;
        return result;
    }

    public Fraction division(Fraction fraction) {
        Fraction result = new Fraction();
        result.nominator = this.nominator * fraction.denominator;
        result.denominator = this.denominator * fraction.nominator;
        return result;
    }

    public Fraction addition(Fraction fraction) {
        Fraction result = new Fraction();
        if (this.denominator == fraction.denominator) {
            result.setnominator(this.nominator + fraction.nominator);
            result.setdenominator(this.denominator);
        } else {
           result.setnominator(this.nominator * fraction.denominator + fraction.nominator * this.denominator);
            result.setdenominator(this.denominator * fraction.denominator);
        }
        return result;
    }

    public Fraction substraction(Fraction fraction) {
        Fraction result = new Fraction();
        if (this.denominator == fraction.denominator) {
            result.nominator = this.nominator - fraction.nominator;
            result.denominator = this.denominator;
        } else {
            result.nominator = this.nominator * fraction.denominator - fraction.nominator * this.denominator;
            result.denominator = this.denominator * fraction.denominator;
        }
        return result;
    }

    public int compareTo(Fraction fraction){
       int result = Integer.compare(this.nominator, fraction.nominator);
        return result;
    }

    public static  class CompareToDenominator implements Comparator<Fraction>
    {
       public int compare (Fraction fraction1, Fraction fraction2 )
       {
           return Integer.compare(fraction1.denominator, fraction2.denominator);
       }
    }


    // --------------------------- ITERATOR ----------------------------------------------//

   private int iterator_idx = 0;

    public void reset() {
        iterator_idx = 0;
    }

    @Override
   public boolean hasNext(){
        return iterator_idx < 2;
   }

    public Iterator<Integer> iterator() {
        reset();
        return this;
    }

    @Override
    public Integer next()
    {
        switch (iterator_idx) {
            case 0:
                iterator_idx++;
                return nominator;
            case 1:
                iterator_idx++;
                return denominator;
            default:
                reset();
                return null;
        }
    }
    @Override
    public String toString() {
        String name = "Fraction : " + "nominator = " + nominator +
                ", denominator = " + denominator;
        return name;
    }



}