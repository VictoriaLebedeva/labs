package com.company;

import java.io.*;

public class Main {

    public static byte[] toBytes (Bill bill ) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(bill);
        out.flush();

        return baos.toByteArray();
    }

    public static long[] write ( Bill [] accounts) throws IOException {

        long[] pointers = new long[accounts.length];
            RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
            int i = 0;
            for (Bill object : accounts) {
                long result = file.length();
                file.seek( result );
                pointers[i] = file.getFilePointer();
                i++;
                file.writeInt(toBytes(object).length );
                file.write(toBytes(object));
                file.setLength( file.getFilePointer());
            }
        return pointers;
    }

    public static Bill fromBytes(byte [] bytes)  throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
            Bill object = (Bill) in.readObject();
           return object;
    }

    public static void read (long [] pointers) throws IOException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        for (int i = 0; i < pointers.length; i++)
        {
            long objectPointer = pointers[i];
            file.seek(objectPointer);
            byte [] buffer = new byte [file.readInt()];
            file.read(buffer);
            System.out.println(fromBytes(buffer));
        }
    }

    public static void main(String[] args) throws IOException {


        Bill [] acconts = new Bill [3];
        acconts[0] = new Bill("KOL","cleaning",12.34,3);
        acconts[1] = new Bill("MOL","washing",6.92,1);
        acconts[2] = new Bill("SOL","repairing",14.64,2);

        try {
            long [] array = write(acconts);
            read(array);
        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
