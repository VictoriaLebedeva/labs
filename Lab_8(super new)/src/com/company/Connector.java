package com.company;

import java.io.*;
import java.util.*;

public class Connector {


    public static byte[] toBytes(Bill bill) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(bill);
        out.flush();

        return baos.toByteArray();
    }

    public static long[] write(Bill[] accounts) throws IOException {

        long[] pointers = new long[accounts.length];
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        int i = 0;
        for (Bill object : accounts) {
            long result = file.length();
            file.seek(result);
            pointers[i] = file.getFilePointer();
            i++;
            file.writeInt(toBytes(object).length);
            file.write(toBytes(object));
            file.setLength(file.getFilePointer());
        }
        return pointers;
    }

    public static Bill fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        Bill object = (Bill) in.readObject();
        return object;
    }

    public static void read(long[] pointers) throws IOException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        for (int i = 0; i < pointers.length; i++) {
            long objectPointer = pointers[i];
            file.seek(objectPointer);
            byte[] buffer = new byte[file.readInt()];
            file.read(buffer);
            System.out.println(fromBytes(buffer));
        }
    }

    public static Map<Object, Long> writeByField(Bill[] accounts, String field) throws FileNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        Map<Object, Long> pointers = new HashMap<Object, Long>();
        try {
            for (Bill object : accounts) {
                long result = file.length();
                file.seek(result);
                pointers.put(object.getFieldName(field), file.getFilePointer());
                file.writeInt(toBytes(object).length);
                file.write(toBytes(object));
                file.setLength(file.getFilePointer());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return pointers;
    }

    public static Bill readByField(Object index, Map<Object, Long> pointers) throws IOException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        file.seek(pointers.get(index));
        byte[] buffer = new byte[file.readInt()];
        file.read(buffer);
        Bill object = fromBytes(buffer);
        assert object != null;
        return object;


    }

    public static void searchByCurrentIndex(Map<Object, Long> pointers, Object index) throws IOException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        ArrayList<Bill> accounts = new ArrayList<Bill>();
        Object[] keys = pointers.keySet().toArray();
        Arrays.sort(keys);
        for (Object i : keys)
            if (i == index) {
                file.seek(pointers.get(i));
                byte[] buffer = new byte[file.readInt()];
                file.read(buffer);
                accounts.add(fromBytes(buffer));
            }
        for (Bill object : accounts) {
            System.out.println(object);
        }

    }

    public static void greaterThanIndex (Map<Object,Long> pointers, Object index) throws IOException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        ArrayList<Bill> accounts = new ArrayList<Bill>();
        Object[] keys = pointers.keySet().toArray();
        Arrays.sort(keys, Collections.reverseOrder());
        for (Object i : keys) {
            if (i.equals(index)) break;
            file.seek(pointers.get(i));
            byte[] buffer = new byte[file.readInt()];
            file.read(buffer);
            accounts.add(fromBytes(buffer));
        }
        for (Bill object : accounts) {
            System.out.println(object);
        }
    }

    public static void lessThanIndex (Map<Object,Long> pointers, Object index) throws IOException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        ArrayList<Bill> accounts = new ArrayList<Bill>();
        Object[] keys = pointers.keySet().toArray();
        Arrays.sort(keys);
        for (Object i : keys) {
            if (i.equals(index)) break;
            file.seek(pointers.get(i));
            byte[] buffer = new byte[file.readInt()];
            file.read(buffer);
            accounts.add(fromBytes(buffer));
        }
        for (Bill object : accounts) {
            System.out.println(object);
        }
    }

    public static void removeByIndex(Object index, Bill [] accounts, String field) throws IOException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        Bill [] buffer = new Bill[accounts.length-1];
       int counter = 0;
        for (Bill i: accounts )
            if (i.getFieldName(field) != index ) {
                buffer[counter] = i;
                counter++;
            }
            file.setLength(0);
            long [] pointers = write(buffer);
            read(pointers);

    }
}


