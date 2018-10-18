package com.company;

import  java.util.Locale;

public class Main {

        public static void main(String[] args)  {

        Tree [] garden = new Tree[6];
        garden[0] = new Apple(4,5);
        garden[1] = new Plum(3,4);
        garden[2] = new Pear(4,5);
        garden[3] = new Cherry(10,2);
        garden[4] = new Apple(7,4);
        garden[5] = new Pear(6,4);

        try {

            if (args.length == 2)
            {
                Locale newLocale = new Locale(args[0], args[1]);
                Internationalization.setLocale( newLocale );
            }

            Connector connector = new Connector("C:\\Users\\Виктория\\IdeaProjects\\labs\\Lab_6(10.10)\\src\\com\\data.dat");
            connector.write(garden);
            Tree [] result = connector.read();

            for (Tree n : result) {
                System.out.println(n);
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}