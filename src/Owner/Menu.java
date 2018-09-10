package Owner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class Menu extends JPanel {
    private JButton addFood = new JButton("Add food");
    private JButton updateFood = new JButton("Update food");
//    private JTable popular = new JTable();
    private JTable allFood;
    private JButton back = new JButton("Back");
    private JTextField pop = new JTextField(50);
    String[] columNames = {"Food ID", "Food Name", "Food Price"};
    String[] colNamew = {"Food Name", "Popularity"};
    Vector col = new Vector(Arrays.asList(colNamew));
    DefaultTableModel tableModel = new DefaultTableModel(columNames,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    DefaultTableModel model = new DefaultTableModel(new Vector<Vector>(),col){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public Menu(){
        allFood = new JTable(tableModel);
        this.add(addFood);
        this.add(updateFood);
        this.add(back);
        this.add(pop);
        JScrollPane jScrollPane = new JScrollPane(allFood);
        this.add(jScrollPane);
    }

    public void setTableView(ArrayList<Object[]> objects, String popularOne){
        if(tableModel.getRowCount() > 0){
            for (int i = tableModel.getRowCount() -1; i>-1; i--)
                tableModel.removeRow(i);
        }
        for(int i = 0; i<objects.size(); i++){
            tableModel.addRow(objects.get(i));
        }
        pop.setText(popularOne);
    }
    public void addListinertoBackButton(ActionListener listener){back.addActionListener(listener);}
    public void addActionListnerToupdateButton (ActionListener listener){updateFood.addActionListener(listener);}
    public void addActionListnerToaddFoodButton (ActionListener listener){addFood.addActionListener(listener);}
}
