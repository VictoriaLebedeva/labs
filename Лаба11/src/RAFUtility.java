import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

public class RAFUtility {

    private String filename;

    public RAFUtility(String filename) {
        this.filename = filename;
    }

    public ArrayList<Bill> readFile() {
        ArrayList<Bill> result = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(this.filename, "r")) {
            long position = 0;
            while ((position = raf.getFilePointer()) < raf.length()) {
                Bill bill = (Bill) Connector.readObject(raf, position);
                result.add(bill);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addToFile(Serializable object) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(this.filename, "rw")) {
            Connector.writeObject(raf, object);
        }
    }

    public void writeAllToFile(DefaultListModel<Bill> data) throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile(this.filename, "rw")) {
            raf.setLength(0);
            for(int i=0;i<data.getSize();++i)
                Connector.writeObject(raf,data.get(i));
        }

    }
}
