package com.company;

import java.io.*;

public class Connector {

    private String filename;

    public Connector (String filename) {this.filename = filename; }

    public void write(Tree [] treeset) throws IOException {
        FileOutputStream output = new FileOutputStream(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(output)){
            out.writeInt(treeset.length);
            for (int i = 0; i < treeset.length; i++)
                out.writeObject(treeset[i]);
            out.flush();
        }
    }

    public Tree[] read() throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream(filename);
        try (ObjectInputStream in = new ObjectInputStream(input)) {
            int length = in.readInt();
            Tree[] result = new Tree[length];
            for (int i = 0; i < length; i++)
                result[i] = (Tree)in.readObject();
            return result;
        }
    }
}

