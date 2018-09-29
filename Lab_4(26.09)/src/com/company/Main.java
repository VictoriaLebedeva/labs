package com.company;

import java.util.Scanner;
public class Main {

    public static Polynom [] createmassive (int degree, int quantity)
    {
        Polynom [] massive = new Polynom[quantity];
        for ( int i = 0; i < quantity ; i++)
        {
            massive[i] = new Polynom (degree);
        }
        return massive;
    }

    public static void printmassive (Polynom [] polynoms)
    {
        for (int i = 0; i < polynoms.length; i++)
        {
            polynoms[i].print();
        }
    }

    public static int[] count (Polynom [] polynoms, int x)
    {
        int [] count = new int [polynoms.length];
        for (int i = 0; i < polynoms.length; i++)
        {
            for (int j = polynoms[i].degree; j >= 0 ; j--)
            count[i] += polynoms[i].coefficients[j] * Math.pow(x,j);
        }
        return count;
    }

    public static int minimum (int [] massive)
    {
        int minimum = massive[1];
        for ( int i = 1; i < massive.length; i++ )
            if (massive[i] < minimum )
                minimum = massive[i];

            return minimum;
    }

    public static int maximum (int [] massive)
    {
        int maximum = massive[1];
        for ( int i = 1; i < massive.length; i++ )
            if (massive[i] > maximum )
                maximum = massive[i];

        return maximum;
    }

    public static void main (String [] args)
    {

        Polynom polynom1 = new Polynom(3);
        Polynom polynom2 = new Polynom(0);

        polynom1.print();
        polynom2.print();

        Polynom summ = polynom1.sum(polynom2);
        System.out.println("Summ : ");
        summ.print();

        Polynom multiply = polynom1.multiply(polynom2);
        System.out.println("multiply : ");
        multiply.print();

        System.out.print("Massive length - ");
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();

        System.out.println("Enter degree of polynom");
        int degree = scanner.nextInt();

        System.out.println("Polynoms: ");
        Polynom [] massive = createmassive( degree, quantity);
        printmassive(massive);

        int [] count = new int [quantity];

        System.out.print("Enter value of x ");
        int x = scanner.nextInt();
        count = count(massive,1);

        for (int i = 0; i < count.length; i++)
        {
            System.out.println((i+1)+ ". x(" + x + ") = " + count[i]);
        }

        int minimum = minimum(count);
        System.out.println("The minimum value in the array : " + minimum);


        int maximum = maximum(count);
        System.out.println("The maximum value in the array : " + maximum);

    }
}
