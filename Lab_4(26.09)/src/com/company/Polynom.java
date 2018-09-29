package com.company;

import java.util.Random;

public class Polynom {
    int degree = 0;
    int[] coefficients;

    public Polynom() {
        degree = 0;
        coefficients = null;
    }

    public Polynom(int n) {
        assert (n >= 0);
        degree = n;
        makepolynom(degree + 1);
    }

    private void makepolynom(int quantity) {
        assert (quantity > 0);
        coefficients = new int[quantity];
        Random random = new Random();

        for (int i = 0; i < quantity; i++) {
            coefficients[i] = random.nextInt(10);
        }
    }

    public void print() {
        for (int i = degree; i >= 0; i--) {
            if (i == 0)
                System.out.print(coefficients[i]);

            else
                System.out.print(coefficients[i] + "x^" + i + " + ");
        }
        System.out.println();
    }

    public Polynom sum(Polynom polynom) {
        int max = Math.max(this.degree, polynom.degree), min = Math.min(this.degree, polynom.degree);
        Polynom result = new Polynom(max);
        for (int i = 0; i <= min; i++) {
            result.coefficients[i] = this.coefficients[i] + polynom.coefficients[i];
        }

        if (this.degree > polynom.degree)
            System.arraycopy(this.coefficients, min + 1, result.coefficients, min + 1, max - min);

        else if (polynom.degree > this.degree)
            System.arraycopy(polynom.coefficients, min + 1, result.coefficients, min + 1, max - min);
        return result;
    }

    public Polynom multiply(Polynom p1) {
        Polynom result = new Polynom(this.degree + p1.degree);
        /*for (int i = this.degree; i >=0; i--) {
            for (int j = p1.degree; j >= 0; j--) {
                result.coefficients[i + j] += this.coefficients[i] * p1.coefficients[j];
            }
        }*/
        for (int i = 0; i < this.degree; i++) {
            for (int j = 0; j < p1.degree; j++) {
                result.coefficients[i + j] += this.coefficients[i] * p1.coefficients[j];
            }

        }return result;
    }
}