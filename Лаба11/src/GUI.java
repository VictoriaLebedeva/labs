import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI extends JFrame {
    private JPanel rootPanel;

    public static DefaultListModel<Bill> listModel = new DefaultListModel<>();
    private JList objectlist;

    private JComboBox selectFieldComboBox;
    private String selectedField = "company";

    private JTextField companytextField;
    private JTextField servicetextField;
    private JTextField costtextField;
    private JTextField volumetextField;
    private JButton ADDButton;

    private JButton inAscendingOrderButton;
    private JButton inDescendingOrderButton;

    String selectedSearchField;
    private JComboBox searchCombobox;
    private JButton applyButton;

    private JTextField removetextField;
    private JButton removeButton;
    private JTextField indextextField;
    private JTextField status;

    public GUI(){
    add(rootPanel);
    setSize(950, 550);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");

        fileMenu.add(load);
        fileMenu.add(save);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        load.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                File file = fileChooser.getSelectedFile();
                RAFUtility fileSupport = new RAFUtility(file.getName());
                ArrayList<Bill> loadedBill = fileSupport.readFile();
                listModel.clear();
                for(Bill object: loadedBill) {
                    listModel.addElement(object);
                }
                objectlist.setModel(listModel);
                status.setText("Data was loaded from file");
            }
        });

        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(rootPanel);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                File file = fileChooser.getSelectedFile();
                RAFUtility files = new RAFUtility(file.getName());
                try {
                    files.writeAllToFile(listModel);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                status.setText("Data was saved in file");
            }
        });

        selectFieldComboBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedField = (String) selectFieldComboBox.getSelectedItem();
            }
        });

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String company = companytextField.getText();
                String service = servicetextField.getText();
                double cost = Double.parseDouble(costtextField.getText());
                int volume = Integer.parseInt(volumetextField.getText());
                listModel.addElement(new Bill(company, service, cost, volume));
                objectlist.setModel(listModel);
                status.setText("A new item was added");
            }
        });

        inAscendingOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bill[] list = new Bill[listModel.getSize()];
                for (int i = 0; i < listModel.getSize(); i++)
                    list[i] = listModel.getElementAt(i);
                if (selectedField == "company")
                    Arrays.sort(list, new SortedByCompany());
                if (selectedField == "service")
                    Arrays.sort(list, new SortedByService());
                if (selectedField == "cost")
                    Arrays.sort(list, new SortedByCost());
                if (selectedField == "volume")
                    Arrays.sort(list, new SortedByVolume());
                listModel.clear();
                for (Bill i : list) {
                    listModel.addElement(i);
                }
                objectlist.setModel(listModel);
                status.setText("Array was sorted in ascending order");
            }
        });

        inDescendingOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bill[] list = new Bill[listModel.getSize()];
                for (int i = 0; i < listModel.getSize(); i++)
                    list[i] = listModel.getElementAt(i);
                if (selectedField == "company")
                    Arrays.sort(list, new SortedByCompany().reversed());
                if (selectedField == "service")
                    Arrays.sort(list, new SortedByService().reversed());
                if (selectedField == "cost")
                    Arrays.sort(list, new SortedByCost().reversed());
                if (selectedField == "volume")
                    Arrays.sort(list, new SortedByVolume().reversed());
                listModel.clear();
                for (Bill i : list) {
                    listModel.addElement(i);
                }
               objectlist.setModel(listModel);
                status.setText("Array was sorted in descending order");
            }
        });

        searchCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSearchField = (String)searchCombobox.getSelectedItem();
            }
        });

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               DefaultListModel<Bill> listModelBuffer = new DefaultListModel<>();
                Bill[] list = new Bill[listModel.getSize()];
                for (int i = 0; i < listModel.getSize(); i++)
                    list[i] = listModel.getElementAt(i);
                int flag = 0;
                String index = indextextField.getText();
                if (selectedSearchField == "current index") {
                    if (selectedField == "company")
                        for (Bill i : list)
                            if (i.getCompany().equals(index)) {
                                listModelBuffer.addElement(i);
                                flag = 1;
                                break;
                            }
                    if (selectedField == "service")
                        for (Bill i : list)
                            if (i.getService().equals(index)) {
                                listModelBuffer.addElement(i);
                                flag = 1;
                                break;
                            }
                    if (selectedField == "cost") {
                        int cost = Integer.parseInt(index);
                        for (Bill i : list)
                            if (i.getCost() == cost) {
                                listModelBuffer.addElement(i);
                                flag = 1;
                                break;
                            }
                    }
                    if (selectedField == "volume") {
                        double volume = Double.parseDouble(index);
                        for (Bill i : list)
                            if (i.getVolume() == volume) {
                                listModelBuffer.addElement(i);
                                flag = 1;
                                break;
                            }
                    }
                    status.setText("Items were found by current index");
                }
                if (selectedSearchField == "less index"){
                    if (selectedField == "company"){
                        Arrays.sort(list, new SortedByCompany());
                        for (Bill i : list){
                            if (i.getCompany().equals(index)) {
                                flag = 1;
                                break;
                            }
                            listModelBuffer.addElement(i);
                        }
                    }
                    if (selectedField == "service") {
                        Arrays.sort(list, new SortedByService());
                        for (Bill i : list) {
                            if (i.getService().equals(index)) {
                                flag = 1;
                                break;
                            }
                            listModel.addElement(i);
                        }
                    }
                    if (selectedField == "cost") {
                        Arrays.sort(list, new SortedByCost());
                        int cost = Integer.parseInt(index);
                        for (Bill i : list) {
                            if (i.getCost() == cost) {
                                flag = 1;
                                break;
                            }
                            listModelBuffer.addElement(i);
                        }
                    }
                    if (selectedField == "volume"){
                        double volume = Double.parseDouble(index);
                        for (Bill i : list){
                            if (i.getVolume() == volume){
                                flag = 1;
                                break;
                            }
                            listModelBuffer.addElement(i);
                        }
                    }
                    status.setText("Items were found by less index");
                }
                if (selectedSearchField == "greater index"){
                    if (selectedField == "company"){
                        Arrays.sort(list, new SortedByCompany().reversed());
                        for (Bill i : list){
                            if (i.getCompany().equals(index)) {
                                flag = 1;
                                break;
                            }
                            listModelBuffer.addElement(i);
                        }
                    }
                    if (selectedField == "service") {
                        Arrays.sort(list, new SortedByService().reversed());
                        for (Bill i : list) {
                            if (i.getService().equals(index)) {
                                flag = 1;
                                break;
                            }
                            listModelBuffer.addElement(i);
                        }
                    }
                    if (selectedField == "cost") {
                        Arrays.sort(list, new SortedByCost().reversed());
                        int cost = Integer.parseInt(index);
                        for (Bill i : list) {
                            if (i.getCost() == cost) {

                                flag = 1;
                                break;
                            }
                            listModelBuffer.addElement(i);
                        }
                    }
                    if (selectedField == "volume") {
                        Arrays.sort(list, new SortedByCost().reversed());
                        double volume = Double.parseDouble(index);
                        for (Bill i : list) {
                            if (i.getVolume() == volume) {
                                flag = 1;
                                break;
                            }
                            listModelBuffer.addElement(i);
                        }
                    }
                    status.setText("Items were found by greater index");
                }
                if(flag == 0) JOptionPane.showMessageDialog(null,"NO matches!");
                objectlist.setModel(listModelBuffer);
            }
        });
    }
}
