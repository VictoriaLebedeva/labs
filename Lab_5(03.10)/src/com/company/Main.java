package com.company;

import java.util.*;
public class Main {

    public static void main(String[] args) {
	Fraction fraction1 = new Fraction(2,3);
	Fraction fraction2 = new Fraction(4,5);

	fraction1.print();

	Fraction result = new Fraction();

	result = fraction1.addition(fraction2);
	System.out.println("Addition");
	result.print();

	result = fraction1.substraction(fraction2);
	System.out.println("Substraction");
	result.print();

	result = fraction1.multiply(fraction2);
	System.out.println("Multiply");
	result.print();

	result = fraction1.division(fraction2);
	System.out.println("Division");
	result.print();

	System.out.println("\n" + " toString ");
	System.out.println(fraction1.toString());

	System.out.println("\n" + "Constructor from string");
	Fraction fraction3 = new Fraction("3,8");
	fraction3.print();

	Fraction [] fractions = {
	 new Fraction(3,5),
	 new Fraction(4,7),
	 new Fraction(1,6),
	 new Fraction(5,8),
	 new Fraction(9,13),};

	System.out.println("\n" + "Sorting by nominator");
	Arrays.sort(fractions);

	for (int i = 0; i < fractions.length; i++)
		System.out.println(fractions[i].toString());

	System.out.println("\n" + "Sorting by denominator");
		Arrays.sort(fractions, new Fraction.CompareToDenominator());
		for (int i = 0; i < fractions.length; i++)
			System.out.println(fractions[i].toString());

		System.out.println("\n" + "Iterator");
		int count = 1;
		while (fraction1.hasNext()) {
			System.out.printf("%d field: %d\n", count++, fraction1.next());
		}
    }
}
