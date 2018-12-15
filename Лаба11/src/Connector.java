import java.io.*;
import java.util.*;

public class Connector {

    public static byte[] toBytes(Serializable object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(object);
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

    public static long writeObject(RandomAccessFile file, Serializable object) throws IOException {
        long result = file.length();
        file.seek(result);
        byte[] temp = toBytes(object);
        file.writeInt(temp.length);
        file.write(temp);
        file.setLength(file.getFilePointer());
        return result;
    }

    public static Object readObject(RandomAccessFile file, long position) throws IOException, ClassNotFoundException {
        file.seek(position);
        int length = file.readInt();
        byte[] objectBytes = new byte[length];
        file.read(objectBytes);
        return fromBytes(objectBytes);
    }
}


