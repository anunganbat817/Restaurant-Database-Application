package Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Menu extends JPanel {
    private JButton make = new JButton("Make Reservation");
    private JTable allFood;
    private JTextField date = new JTextField(12);
    private JTextField phone = new JTextField(12);
    private JTextField name = new JTextField(12);
    private JTextField numberofCus = new JTextField(12);

    private JButton back = new JButton("Back");
    String[] columNames = {"Food ID", "Food Name", "Food Price"};
    DefaultTableModel tableModel = new DefaultTableModel(columNames,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public Menu(){
        allFood = new JTable(tableModel);
        allFood.setRowSelectionAllowed(true);
        allFood.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(make);
        this.add(back);
        this.add(new JLabel("When to eat: "));
        this.add(date);
        this.add(new JLabel("your phone: "));
        this.add(phone);
        this.add(new JLabel("your Name: "));
        this.add(name);
        this.add(new JLabel("How many are coming: "));
        this.add(numberofCus);
        JScrollPane jScrollPane = new JScrollPane(allFood);
        this.add(jScrollPane);
    }

    public void setTableView(ArrayList<Object[]> objects){
        if(tableModel.getRowCount() > 0){
            for (int i = tableModel.getRowCount() -1; i>-1; i--)
                tableModel.removeRow(i);
        }
        for(int i = 0; i<objects.size(); i++){
            tableModel.addRow(objects.get(i));
        }
    }

    public ArrayList<Integer> getFoodIDs(){
        ArrayList<Integer> ids=new ArrayList<Integer>();
        int[] rows = allFood.getSelectedRows();
        for(int i = 0; i<rows.length;i++){
            int id = Integer.parseInt(allFood.getValueAt(rows[i],0).toString());
            System.out.println(allFood.getValueAt(rows[i],0).toString());
            ids.add(id);
        }
        return ids;
    }

    public String getCName(){
        return name.getText();
    }

    public String getDate(){
        return date.getText();
    }

    public String getPhone(){
        return phone.getText();
    }

    public String getNumberOfCus(){
        return numberofCus.getText();
    }
    public void addListinertoBackButton(ActionListener listener){back.addActionListener(listener);}
    public void addListinertoMakebutton(ActionListener listener){make.addActionListener(listener);}
}
