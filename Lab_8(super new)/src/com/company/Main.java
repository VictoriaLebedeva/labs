package com.company;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        Bill[] accounts = new Bill[3];
        accounts[0] = new Bill("KOL", "cleaning", 12.34, 3);
        accounts[1] = new Bill("MOL", "washing", 6.92, 1);
        accounts[2] = new Bill("SOL", "repairing", 14.64, 2);

        try {

            Map<Object, Long> pointers = Connector.writeByField(accounts, "service");
            Object[] keys = pointers.keySet().toArray();

            System.out.println("Output in ascending order");
            Arrays.sort(keys);
            for (Object i : keys) {
                Bill res = Connector.readByField(i, pointers);
                assert res != null;
                System.out.println(res.toString());
            }

            System.out.println("\nOutput in descending order");
            Arrays.sort(keys, Collections.reverseOrder());
            for (Object i : keys) {
                Bill res = Connector.readByField(i, pointers);
                assert res != null;
                System.out.println(res.toString());
            }

            System.out.println("\nSearching by index");
            Connector.searchByCurrentIndex(pointers, accounts[2].getFieldName("service"));

            System.out.println("\nSearching by greater index");
            Connector.greaterThanIndex(pointers, accounts[2].getFieldName("service"));

            System.out.println("\nSearching by less index");
            Connector.lessThanIndex(pointers, accounts[2].getFieldName("service"));

            System.out.println("\nRemoving by index");
            Connector.removeByIndex(accounts[0].getFieldName("service"), accounts ,"service" );


        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
